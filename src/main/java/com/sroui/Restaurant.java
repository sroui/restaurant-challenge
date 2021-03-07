package com.sroui;

import com.sroui.exceptions.UnavailableDishException;

import static com.sroui.utils.TextUtils.isNumeric;

import java.util.*;

//todo think to externalize Stock
public class Restaurant {
    public static final int UNLIMITED_QUANTITY = -1;
    private static final int AMOUNT_INDEX = 0;
    private static final int STOCK_ITEM_NAME_INDEX = 1;

    private final HashMap<String, Recipe> recipeByName;
    private Map<String, Integer> stockItemToQuantity;

    public Restaurant(String... stockItems) {
        stockItemToQuantity = new HashMap<>();
        recipeByName = new HashMap<>();

        initStock(stockItems);
        initRecipes();
    }

    private void initStock(String... stockItems) {
        for (String stockItem: stockItems) {

            if(stockItem.contains(" ")) {
                String itemQuantity = stockItem.split(" ",2)[AMOUNT_INDEX];
                String itemName = stockItem.split(" ", 2)[STOCK_ITEM_NAME_INDEX];

                if (isNumeric(itemQuantity)) {
                    stockItemToQuantity.put(itemName, Integer.parseInt(itemQuantity));
                    continue;
                }
            }

            stockItemToQuantity.put(stockItem, UNLIMITED_QUANTITY);
        }
    }

    private void initRecipes() {
        String recipeName = "Tomato Mozzarella Salad";

        Recipe recipe = new Recipe(recipeName);
        recipe.addIngredient("balls Mozzarella", 1);
        recipe.addIngredient("tomatoes", 2);
        recipe.addIngredient("olive oil", UNLIMITED_QUANTITY);
        recipe.setPreparationTimeInMinutes(6);

        recipeByName.put(recipeName, recipe);
    }

    public Ticket order(String order) throws UnavailableDishException {
        Objects.requireNonNull(order, "You should specify the order");
        Ticket ticket = parseTicketFromOrder(order);
        // todo query and command separation (create canPrepare and prepare)
        prepare(ticket);
        return ticket;
    }

    private Ticket parseTicketFromOrder(String order) {
        String[] orderSplits = order.split(" ",2);

        int servedDishes;
        String orderedRecipeName;

        if (orderSplits.length == 2) {
            servedDishes = Integer.parseInt(orderSplits[0]);
            orderedRecipeName = orderSplits[1];
        } else {
            servedDishes = UNLIMITED_QUANTITY;
            orderedRecipeName = order;
        }

        return new Ticket(orderedRecipeName, servedDishes);
    }

    private void prepare(Ticket ticket) throws UnavailableDishException {
        Recipe orderedRecipe =  recipeByName.get(ticket.getRecipeName());
        stockItemToQuantity = verifyStockAvailabilityFor(orderedRecipe, ticket.getServedDishes());
    }

    private Map<String, Integer>
    verifyStockAvailabilityFor(final Recipe orderedRecipe, final int servedDishes) throws UnavailableDishException {
        // todo refactor code or rename function because it contains side effects
        Map<String, Integer> ingredientToAmountMap = orderedRecipe.getIngredientToAmountMap();
        Map<String, Integer> stockItemToAmountNewMap = new HashMap<>();

        for (Map.Entry<String, Integer> entry : ingredientToAmountMap.entrySet()) {
            verifyStockAvailabilityFor(entry.getKey(), entry.getValue() * servedDishes);
            stockItemToAmountNewMap.put(entry.getKey(),
                                        stockItemToQuantity.get(entry.getKey()) - entry.getValue() * servedDishes);
        }

        return stockItemToAmountNewMap;
    }

    private void verifyStockAvailabilityFor(String ingredient, int amount) throws UnavailableDishException {
        boolean isStockContainsIngredient = stockItemToQuantity.containsKey(ingredient);

        boolean isNeededAmountAvailable = isStockContainsIngredient
                && ( amount == UNLIMITED_QUANTITY || stockItemToQuantity.get(ingredient) - amount >= 0);

        if (!(isStockContainsIngredient && isNeededAmountAvailable)) {
            throw new UnavailableDishException("The ingredient '" + ingredient + "' is out of stock");
        }
    }

    public Meal retrieveMealFor(Ticket ticket) {
        Recipe recipe = recipeByName.get(ticket.getRecipeName());
        return new Meal(recipe, ticket.getServedDishes());
    }

    public double getStockQuantityOf(String itemName) {
        return stockItemToQuantity.get(itemName);
    }
}
