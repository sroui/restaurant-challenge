package com.sroui.restaurant.service;

import com.sroui.restaurant.meal.Meal;

public interface MealService {
    Meal loadMeal(String recipeName, int dishCount);
}
