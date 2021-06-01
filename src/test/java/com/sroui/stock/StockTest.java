package com.sroui.stock;

import com.sroui.stock.exceptions.ItemOutOfStockException;
import com.sroui.stock.exceptions.NotEnoughItemAmountInStockException;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static com.sroui.stock.StockItem.UNLIMITED_AMOUNT;
import static org.fest.assertions.Assertions.assertThat;

public class StockTest {
    private static Stock stock;

    @BeforeClass
    public static void setup() {
        String[] stockItemsAsString = {"6 balls Mozzarella", "20 tomatoes", "olive oil", "pepper"};
        stock = new Stock(stockItemsAsString);
    }

    @Test(expected = ItemOutOfStockException.class)
    public void
    shouldThrowExceptionIfItemIsOutOfStock() throws ItemOutOfStockException, NotEnoughItemAmountInStockException {
        List<StockItem> askedItems = new ArrayList<>();
        askedItems.add(new StockItem("potatoes", 10));

        stock.retrieveAll(askedItems);
    }

    @Test(expected = NotEnoughItemAmountInStockException.class)
    public void
    throwExceptionIfNotEnoughItemAmountInStock() throws ItemOutOfStockException, NotEnoughItemAmountInStockException {
        List<StockItem> askedItems = new ArrayList<>();
        askedItems.add(new StockItem("balls mozzarella", 7));

        stock.retrieveAll(askedItems);
    }

    @Test
    public void shouldRetrieveItems() throws ItemOutOfStockException, NotEnoughItemAmountInStockException {
        List<StockItem> askedItems = new ArrayList<>();
        askedItems.add(new StockItem("balls mozzarella", 2));
        askedItems.add(new StockItem("tomatoes", 20));
        askedItems.add(new StockItem("olive oil", UNLIMITED_AMOUNT));

        stock.retrieveAll(askedItems);

        assertThat(stock.getAmountOf("balls mozzarella")).isEqualTo(4); // before 20, after 4
        assertThat(stock.getAmountOf("tomatoes")).isEqualTo(0); // before 20, after 0
        assertThat(stock.getAmountOf("olive oil")).isEqualTo(UNLIMITED_AMOUNT); // before : unlimited, after: unlimited
    }
}
