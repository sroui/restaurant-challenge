package com.sroui.restaurant.exceptions;

import java.util.List;

public class NotEnoughAmountInStockException extends RestaurantException{
    public NotEnoughAmountInStockException(String message) {
        super(message);
    }

    public static String buildMessageFromItemsWithNotEnoughAmount(List<String> itemsWithNotEnoughAmountInStock) {
        String message;

        if (itemsWithNotEnoughAmountInStock.size() == 1) {
            message = "We do not have enough amount in stock for item '" + itemsWithNotEnoughAmountInStock.get(0) + "'";
        } else {
            String itemsSeperatedByComma = String.join(", ", itemsWithNotEnoughAmountInStock);
            message = "We do not have enough amount in stock for items '" + itemsSeperatedByComma + "'";
        }

        return message;
    }
}
