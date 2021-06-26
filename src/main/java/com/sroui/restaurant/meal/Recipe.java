package com.sroui.restaurant.meal;

import java.util.HashMap;


public class Recipe {
    private final HashMap<String, Integer> ingredientToAmount;

    private int preparationTimeInMinutes;

    public Recipe() {
        this.ingredientToAmount = new HashMap<>();
    }

    public void addIngredient(String ingredient, Integer amount) {
        ingredientToAmount.put(ingredient, amount);
    }

    public void setPreparationTimeInMinutes(int preparationTimeInMinutes) {
        this.preparationTimeInMinutes = preparationTimeInMinutes;
    }

    public Ingredients createIngredients(int dishCount){
        return Ingredients.fromIngredientToAmountMap(ingredientToAmount, dishCount);
    }

    public int getPreparationTimeInMinutes(int dishCount) {
        return  dishCount * preparationTimeInMinutes;
    }
}
