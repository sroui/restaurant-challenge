package com.sroui.restaurant.exceptions;

public class UnavailableDishException extends RestaurantException {
    public UnavailableDishException(String message, Throwable cause) {
        super(message, cause);
    }
}
