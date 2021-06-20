package com.sroui;

import com.sroui.stock.StockItem;

import java.util.*;

public class Recipe {
    private final String name;
    private final List<StockItem> ingredients;
    private int preparationTimeInMinutes;

    public Recipe(String name) {
        this.name = name;
        this.ingredients = new ArrayList<>();
    }

    public void addIngredient(StockItem ingredient) {
        ingredients.add(ingredient);
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

    // todo Demeter law violation because it exposes its internals by exposing the ingredients
    public List<StockItem> getIngredients(){
        return ingredients;
    }
}
