package com.sroui;

import com.sroui.restaurant.Restaurant;
import com.sroui.restaurant.exceptions.UnavailableDishException;
import com.sroui.restaurant.meal.Meal;
import com.sroui.restaurant.meal.Ticket;
import org.junit.Ignore;
import org.junit.Test;

import static com.sroui.restaurant.stock.Stock.ITEM_UNLIMITED_AMOUNT;
import static org.fest.assertions.Assertions.assertThat;

// TODO use unchecked exceptions instead of checked exception
/*recipe of Tomato Mozzarella Salad is
     *
     * 1 ball of Mozzarella
     * 2 tomatoes
     * some olive oil
     *
     * preparation time is 6 minutes
     *
     * regarding Restaurant stocks :
     *   if there is not quantity it means that the ingredient is in stock and unlimited. (here it concern olive oil and pepper)
     */
public class RestaurantTest {

    @Test
    public void shouldReturnStockAmountOfEachIngredient() {
        Restaurant restaurant = new Restaurant("6 balls Mozzarella", "20 tomatoes", "olive oil", "pepper");

        Double ballsMozzarellaAmount = restaurant.getStockAmountOf("balls Mozzarella");
        Double tomatoesAmount = restaurant.getStockAmountOf("tomatoes");
        Double oliveOilAmount = restaurant.getStockAmountOf("olive oil");
        Double pepperAmount = restaurant.getStockAmountOf("pepper");

        assertThat(ballsMozzarellaAmount).isEqualTo(6);
        assertThat(tomatoesAmount).isEqualTo(20);
        assertThat(oliveOilAmount).isEqualTo(ITEM_UNLIMITED_AMOUNT);
        assertThat(pepperAmount).isEqualTo(ITEM_UNLIMITED_AMOUNT);
    }

    @Test
    public void shouldServeTomatoMozzarellaSalad() {
        Restaurant restaurant = new Restaurant("6 balls Mozzarella", "2 tomatoes", "olive oil", "pepper");

        Ticket ticket = restaurant.order("1 Tomato Mozzarella Salad");
        Meal meal = restaurant.retrieveMealFor(ticket);

        assertThat(meal.servedDishes()).isEqualTo(1);
        assertThat(meal.cookingDuration()).isEqualTo(6);
    }

    /**
     * write a test to ensure that when a recipe require out of stocks ingredients we receive an UnavailableDishException (unchecked)
     */
    @Test(expected = UnavailableDishException.class)
    public void shouldFailWhenOutOfStock() {
        Restaurant restaurant = new Restaurant("6 balls Mozzarella", "20 tomatoes", "olive oil", "pepper");

        restaurant.order("4 Tomato Mozzarella Salad");
        restaurant.order("2 Tomato Mozzarella Salad");
        restaurant.order("1 Tomato Mozzarella Salad");
    }

    /**
     * when cooking more than one dish of the same type :
     * first dish takes standard time, subsequents dish have their cooking time halved
     *
     * here : first = 6 ; 2nd = 3 ; 3rd = 3 ; 4th = 3 => 15 minutes
     */
    @Test
    public void shouldCookFasterWhenDoingMultipleInstanceOfTheSameDish() {
        Restaurant restaurant = new Restaurant("6 balls Mozzarella", "20 tomatoes", "olive oil", "sea salt");
        Ticket ticket = restaurant.order("4 Tomato Mozzarella Salad");
        Meal meal = restaurant.retrieveMealFor(ticket);
        assertThat(meal.servedDishes()).isEqualTo(4);
        assertThat(meal.cookingDuration()).isEqualTo(15);
    }

    /**
     * recipe for a Pizza is
     *
     * 1 ball of Mozzarella
     * 4 tomatoes
     * some olive oil
     * 100cl of water
     * 300g of Flour
     * sea salt
     *
     * cooking time is 10 minutes
     * baking time is 10 minutes at 300 degree celsius
     *
     *
     * Regarding baking :
     *   oven have unlimited capacity multiple dishes can be baked at the same time
     */
    @Test
    @Ignore("Ignored because I haven't add implementation yet")
    public void shouldServeMixedOrders() {
        Restaurant restaurant = new Restaurant("1Kg Flour", "50 tomatoes", "sea salt", "6 balls Mozzarella", "olive oil", "water");
        Ticket ticket = restaurant.order("3 Tomato Mozzarella Salad").and("2 Pizza");
        Meal meal = restaurant.retrieveMealFor(ticket);
        assertThat(meal.servedDishes()).isEqualTo(5);
        assertThat(meal.cookingDuration()).isEqualTo(27);
    }
}