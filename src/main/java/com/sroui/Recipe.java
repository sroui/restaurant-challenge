package com.sroui;

import java.util.HashMap;
import java.util.Map;

public class Recipe {
    private final String name;
    private final Map<String, Integer> ingredientToAmountMap;
    private int preparationTimeInMinutes;

    public Recipe(String name) {
        this.name = name;
        this.ingredientToAmountMap = new HashMap<>();
    }

    public void addIngredient(String ingredient, int amount) {
        ingredientToAmountMap.put(ingredient, amount);
    }

    public String getName() {
        return name;
    }

    public int getPreparationTimeInMinutes() {
        return preparationTimeInMinutes;
    }

    public void setPreparationTimeInMinutes(int preparationTimeInMinutes) {
        this.preparationTimeInMinutes = preparationTimeInMinutes;
    }

    public Map<String, Integer> getIngredientToAmountMap(){
        return new HashMap<>(ingredientToAmountMap);
    }
}
