package com.sroui.restaurant.meal;

import com.sroui.restaurant.service.MealService;
import com.sroui.restaurant.service.impl.MealServiceImpl;

import static com.sroui.restaurant.stock.Stock.ITEM_UNLIMITED_AMOUNT;

public class Meal {
	private final Recipe recipe;
	private final int servedDishes;

	// TODO look I should delete bellow constructor
	public Meal(Recipe recipe, int servedDishes) {
		this.recipe = recipe;
		this.servedDishes = servedDishes;
	}

	// TODO refactor longish method
	public static Meal parseMealFromOrder(String order) {
		String[] orderSplits = order.split(" ",2);

		int servedDishes;
		String orderedRecipeName;

		if (orderSplits.length == 2) {
			servedDishes = Integer.parseInt(orderSplits[0]);
			orderedRecipeName = orderSplits[1];
		} else {
			servedDishes = ITEM_UNLIMITED_AMOUNT;
			orderedRecipeName = order;
		}

		MealService mealService = new MealServiceImpl();
		return mealService.loadMeal(orderedRecipeName, servedDishes);
	}

	public int servedDishes() {
		return servedDishes;
	}

	public int cookingDuration() {
		return recipe.getPreparationTimeInMinutes(servedDishes);
	}

	public Ingredients getNeededIngredients(){
		return recipe.createIngredients(servedDishes);
	}
}
