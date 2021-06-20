package com.sroui.stock;

import static com.sroui.utils.TextUtils.isBlank;

public class StockItem {
    public static final int UNLIMITED_AMOUNT = -1;

    private static final int AMOUNT_INDEX = 0;
    private static final int STOCK_ITEM_NAME_INDEX = 1;
    private static final String ITEM_NAME_AND_AMOUNT_SEPARATOR = " ";

    private final String name;
    private final Integer amount;

    public StockItem(String name, Integer amount) {
        this.name = name;
        this.amount = amount < 0 ? UNLIMITED_AMOUNT : amount;
    }

    public static StockItem fromString(final String itemAsString) {
        if(isBlank(itemAsString)) {
            return null;
        }
        if(startsWithNumber(itemAsString)) {
            String itemAmount = itemAsString.split(ITEM_NAME_AND_AMOUNT_SEPARATOR,2)[AMOUNT_INDEX];
            String itemName = itemAsString.split(ITEM_NAME_AND_AMOUNT_SEPARATOR, 2)[STOCK_ITEM_NAME_INDEX];
            return new StockItem(itemName, Integer.parseInt(itemAmount));
        } else {
            return new StockItem(itemAsString, UNLIMITED_AMOUNT);
        }
    }

    public static boolean startsWithNumber(String s) {
        char ch = s.charAt(0);
        return Character.isDigit(ch);
    }

    // Todo: Demeter law violation because it expose internals of StockItem
    public Integer getAmount() {
        return amount;
    }

    // Todo: Demeter law violation because it expose internals of StockItem
    public String getName() {
        return name;
    }
}
