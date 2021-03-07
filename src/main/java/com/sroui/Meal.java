package com.sroui;

public class Meal {
	private final Recipe recipe;
	private final int servedDishes;

	public Meal(Recipe recipe, int servedDishes) {
		this.recipe = recipe;
		this.servedDishes = servedDishes;
	}

	public int servedDishes() {
		return servedDishes;
	}

	public int cookingDuration() {
		return servedDishes * recipe.getPreparationTimeInMinutes();
	}
}
