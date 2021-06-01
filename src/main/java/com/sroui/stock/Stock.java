package com.sroui.stock;

import com.sroui.stock.exceptions.ItemOutOfStockException;
import com.sroui.stock.exceptions.NotEnoughItemAmountInStockException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.sroui.stock.StockItem.UNLIMITED_AMOUNT;


public class Stock {
    private final Map<String, StockItem> nameToItem = new HashMap<>();

    public Stock(String... itemAsStrings) {
       loadItems(itemAsStrings);
    }

    private void loadItems(String... itemAsStrings) {
        for (String itemAsString: itemAsStrings) {
            store(StockItem.fromString(itemAsString));
        }
    }

    private void store(StockItem item) {
        if (item == null) {
            return;
        }
        nameToItem.put(item.getName().toLowerCase(), item);
    }

    public List<StockItem>
    retrieveAll(final List<StockItem> items) throws ItemOutOfStockException, NotEnoughItemAmountInStockException {
        for (StockItem item : items) {
           retrieveAndUpdateItemAmount(item);
        }
        return items;
    }

    private StockItem
    retrieveAndUpdateItemAmount(StockItem item) throws ItemOutOfStockException, NotEnoughItemAmountInStockException {
        throwExceptionIfItemDoesNotExist(item);
        throwExceptionIfAmountIsNotAvailable(item);
        int askedAmount = item.getAmount();
        int existingAmount = getAmountOf(item.getName());
        int newAmount = askedAmount == UNLIMITED_AMOUNT ? UNLIMITED_AMOUNT : existingAmount - askedAmount;

        updateItemAmount(item.getName(), newAmount);

        return item;
    }

    private void updateItemAmount(String itemName, Integer newAmount) {
        nameToItem.put(itemName.toLowerCase(), new StockItem(itemName, newAmount));
    }

    private void throwExceptionIfItemDoesNotExist(StockItem item) throws ItemOutOfStockException{
        throwExceptionIfItemDoesNotExist(item.getName());
    }

    private void throwExceptionIfItemDoesNotExist(String itemName) throws ItemOutOfStockException{
        if(!contains(itemName)) {
            throw new ItemOutOfStockException("Item '" + itemName + "' is out of stock");
        }
    }

    private void throwExceptionIfAmountIsNotAvailable(StockItem item)
            throws NotEnoughItemAmountInStockException, ItemOutOfStockException {
        if(!containsEnoughItemAmount(item)) {
            String errMessage = "Amount of item '" + item.getName() + "' is not enough in stock";
            throw new NotEnoughItemAmountInStockException(errMessage);
        }
    }

    private boolean containsEnoughItemAmount(StockItem item) throws ItemOutOfStockException {
        int askedAmount = item.getAmount();
        int existingAmount = getAmountOf(item.getName());
        return  askedAmount == UNLIMITED_AMOUNT || existingAmount - askedAmount >= 0;
    }

    private boolean contains(String itemName) {
        return nameToItem.containsKey(itemName.toLowerCase());
    }

    public int getAmountOf(String itemName) throws ItemOutOfStockException {
        throwExceptionIfItemDoesNotExist(itemName);
        return nameToItem.get(itemName.toLowerCase()).getAmount();
    }
}
