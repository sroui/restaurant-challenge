package com.sroui.restaurant.meal;

import com.sroui.restaurant.stock.StockItems;

import java.util.Map;
import java.util.Set;

public class Ingredients extends StockItems {


    public static Ingredients
    fromIngredientToAmountMap(Map<String, Integer> ingredientToAmount, int amountMultiplyFactor) {
        Ingredients ingredients = new Ingredients();
        Set<Map.Entry<String, Integer>> ingredientAndAmountEntries = ingredientToAmount.entrySet();

        ingredientAndAmountEntries.forEach((ingredientAndAmount -> {
            String ingredientName = ingredientAndAmount.getKey();
            Integer amount = ingredientAndAmount.getValue() * amountMultiplyFactor;

            ingredients.put(ingredientName, amount);
        }));

        return ingredients;
    }
}
