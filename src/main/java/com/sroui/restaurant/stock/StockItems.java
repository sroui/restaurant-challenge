package com.sroui.restaurant.stock;

import com.sroui.restaurant.exceptions.ItemOutOfStockException;
import com.sroui.restaurant.exceptions.NotEnoughAmountInStockException;

import java.util.*;

import static com.sroui.restaurant.stock.Stock.ITEM_UNLIMITED_AMOUNT;
import static com.sroui.restaurant.utils.TextUtils.isNotBlank;
import static com.sroui.restaurant.utils.TextUtils.startsWithNumber;

public class StockItems {
    private static final String ITEM_NAME_AND_AMOUNT_SEPARATOR = " ";
    private static final int ITEM_NAME_INDEX = 1;
    private static final int ITEM_AMOUNT_INDEX = 0;

    private final Map<String, Integer> itemNameToAmount = new HashMap<>();

    // todo refactor method because it has mor than two level indentation
    public static StockItems fromItemsAsString(String... itemsAsString) {
        StockItems stockItems = new StockItems();

        for (String item : itemsAsString) {
            if(isNotBlank(item)) {
                if(startsWithNumber(item)) {
                    String itemAmount = item.split(ITEM_NAME_AND_AMOUNT_SEPARATOR,2)[ITEM_AMOUNT_INDEX];
                    String itemName = item.split(ITEM_NAME_AND_AMOUNT_SEPARATOR, 2)[ITEM_NAME_INDEX];
                    stockItems.put(itemName, Integer.parseInt(itemAmount));
                } else {
                    stockItems.put(item, ITEM_UNLIMITED_AMOUNT);
                }
            }
        }

        return stockItems;
    }

    // todo refactor the bellow longish method
    StockItems retrieveAndUpdateItems(StockItems askedItems) {
        assertExistenceOfItems(askedItems);
        assertExistenceOfAskedAmountForItems(askedItems);

        Set<Map.Entry<String, Integer>> entries = askedItems.itemNameToAmount.entrySet();

        entries.forEach(itemAndAmount -> {
            String askedItemName = itemAndAmount.getKey();
            int askedAmount = itemAndAmount.getValue();
            int existingAmount = itemNameToAmount.get(askedItemName);
            int newAmount = askedAmount == ITEM_UNLIMITED_AMOUNT ? ITEM_UNLIMITED_AMOUNT : existingAmount - askedAmount;
            itemNameToAmount.put(askedItemName, newAmount);
        });


        return askedItems;
    }

    // todo refactor the bellow longish method
    private void assertExistenceOfItems(StockItems items) {
        List<String> nonExistingItems = new ArrayList<>();
        for (String itemName : items.itemNameToAmount.keySet()) {
            if (!contains(itemName)) {
                nonExistingItems.add(itemName);
            }
        }
        if (!nonExistingItems.isEmpty()) {
            String errorMessage = ItemOutOfStockException.buildMessageFromOutOfStockItems(nonExistingItems);
            throw new ItemOutOfStockException(errorMessage);
        }
    }

    // todo refactor the bellow longish method
    private void assertExistenceOfAskedAmountForItems(StockItems askedItems) {
        List<String> itemsWithNotEnoughAmount = new ArrayList<>();

        for (Map.Entry<String, Integer> itemNameAndAmount : askedItems.itemNameToAmount.entrySet()) {
            String askedItemName = itemNameAndAmount.getKey();
            Integer askedAmount = itemNameAndAmount.getValue();
            if(!containsEnoughItemAmount(askedItemName, askedAmount)) {
                itemsWithNotEnoughAmount.add(askedItemName);
            }
        }

        if (!itemsWithNotEnoughAmount.isEmpty()) {
            String errorMessage = NotEnoughAmountInStockException.buildMessageFromItemsWithNotEnoughAmount(itemsWithNotEnoughAmount);
            throw new NotEnoughAmountInStockException(errorMessage);
        }
    }

    private boolean containsEnoughItemAmount(String itemName, Integer askedAmount) {
        Integer existingAmount = itemNameToAmount.get(itemName);
        return  askedAmount == ITEM_UNLIMITED_AMOUNT || existingAmount - askedAmount >= 0;
    }

    int getAmountOf(String itemName) {
        Integer itemAmount = itemNameToAmount.get(itemName);

        if (itemAmount == null) {
            throw new ItemOutOfStockException("Item '" + itemName + "' does not exist in stock");
        }

        return itemAmount;
    }

    private boolean contains(String itemName) {
        return itemNameToAmount.containsKey(itemName);
    }

    public void put(String itemName, Integer amount) {
        itemNameToAmount.put(itemName, amount);
    }
}