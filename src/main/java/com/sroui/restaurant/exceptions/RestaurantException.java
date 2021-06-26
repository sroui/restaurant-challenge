package com.sroui.restaurant.exceptions;

// I have used unchecked exception because checked exceptions lead to code that violates Open/Closed principle
public class RestaurantException extends RuntimeException{

    public RestaurantException(String message) {
        super(message);
    }

    public RestaurantException(String message, Throwable cause) {
        super(message, cause);
    }
}
