package com.sroui.restaurant.exceptions;

import java.util.List;

public class ItemOutOfStockException extends RestaurantException {
    public ItemOutOfStockException(String message) {
        super(message);
    }

    public static String buildMessageFromOutOfStockItems(List<String> outOfStockItems) {
        String message;

        if (outOfStockItems.size() == 1) {
            message = "Item '" + outOfStockItems.get(0) + "' does not exist in stock";
        } else {
            String itemsSeperatedByComma = String.join(", ", outOfStockItems);
            message = "Items '" + itemsSeperatedByComma + "' does not exist in stock";
        }

        return message;
    }
}
