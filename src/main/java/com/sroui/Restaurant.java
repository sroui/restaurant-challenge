package com.sroui;

import com.sroui.exceptions.UnavailableDishException;
import com.sroui.stock.Stock;
import com.sroui.stock.StockItem;
import com.sroui.stock.exceptions.ItemOutOfStockException;
import com.sroui.stock.exceptions.NotEnoughItemAmountInStockException;

import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static com.sroui.stock.StockItem.UNLIMITED_AMOUNT;

public class Restaurant {
    private final HashMap<String, Recipe> nameToRecipe;
    private final Stock stock;

    public Restaurant(String... stockItems) {
        stock = new Stock(stockItems);
        nameToRecipe = new HashMap<>();
        initRecipes();
    }

    private void initRecipes() {
        // todo create a service who loads a recipe from different sources e.g. from a properties file
        String recipeName = "Tomato Mozzarella Salad";
        Recipe recipe = new Recipe(recipeName);
        recipe.addIngredient(new StockItem("balls Mozzarella", 1));
        recipe.addIngredient(new StockItem("tomatoes", 2));
        recipe.addIngredient(new StockItem("olive oil", UNLIMITED_AMOUNT));
        recipe.setPreparationTimeInMinutes(6);
        nameToRecipe.put(recipeName, recipe);
    }

    public Ticket order(String order) throws UnavailableDishException {
        Objects.requireNonNull(order, "You should specify the order");
        Ticket ticket = Ticket.parseTicketFromOrder(order);
        prepare(ticket);
        return ticket;
    }

    private void prepare(Ticket ticket) throws UnavailableDishException {
        try {
            stock.retrieveAll(makeIngredientListThatWillBeRetrievedFromStock(ticket));
        } catch (ItemOutOfStockException | NotEnoughItemAmountInStockException e) {
            throw new UnavailableDishException("We could not prepare recipe", e);
        }
    }

    private List<StockItem> makeIngredientListThatWillBeRetrievedFromStock(Ticket ticket) {
        int dishCount = ticket.getDishCount();
        return nameToRecipe.get(ticket.getRecipeName()).getIngredients().stream()
                .map(item -> new StockItem(item.getName(), item.getAmount() * dishCount))
                .collect(Collectors.toList());
    }

    public Meal retrieveMealFor(Ticket ticket) {
        Recipe recipe = nameToRecipe.get(ticket.getRecipeName());
        return new Meal(recipe, ticket.getDishCount());
    }

    public double getStockAmountOf(String itemName) throws ItemOutOfStockException {
        return stock.getAmountOf(itemName);
    }
}
