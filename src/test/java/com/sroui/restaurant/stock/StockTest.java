package com.sroui.restaurant.stock;

import com.sroui.restaurant.exceptions.ItemOutOfStockException;
import org.junit.BeforeClass;
import org.junit.Test;

import static com.sroui.restaurant.stock.Stock.ITEM_UNLIMITED_AMOUNT;
import static org.fest.assertions.Assertions.assertThat;

public class StockTest {
    private static Stock stock;

    @BeforeClass
    public static void setup() {
        String[] stockItemsAsString = {"6 balls Mozzarella", "20 tomatoes", "olive oil", "pepper"};
        stock = new Stock(stockItemsAsString);
    }

    @Test
    public void shouldStockItemsAndTheirAmounts() {
        assertThat(stock.getAmountOf("balls Mozzarella")).isEqualTo(6);
        assertThat(stock.getAmountOf("tomatoes")).isEqualTo(6);
        assertThat(stock.getAmountOf("olive oil")).isEqualTo(ITEM_UNLIMITED_AMOUNT);
        assertThat(stock.getAmountOf("pepper")).isEqualTo(ITEM_UNLIMITED_AMOUNT);
    }

//    @Test(expected = ItemOutOfStockException.class)
//    public void
//    shouldThrowExceptionIfItemIsOutOfStock() throws ItemOutOfStockException, NotEnoughItemAmountInStockException {
//        List<StockItems> askedItems = new ArrayList<>();
//        askedItems.add(new StockItems("potatoes", 10));
//
//        stock.retrieveAll(askedItems);
//    }

//    @Test(expected = NotEnoughItemAmountInStockException.class)
//    public void shouldThrowExceptionIfNotEnoughItemAmountInStock() throws ItemOutOfStockException,
//            NotEnoughItemAmountInStockException {
//        List<StockItems> askedItems = new ArrayList<>();
//        askedItems.add(new StockItems("balls mozzarella", 7));
//
//        stock.retrieveAll(askedItems);
//    }

//    @Test
//    public void shouldExposeItemsForRetrievalAndUpdatesStockItems() throws ItemOutOfStockException, NotEnoughItemAmountInStockException {
//        String[] askedItems = {"2 balls mozzarella", "20 tomatoes", "olive oil"};
//
//        stock.retrieveAll(askedItems);
//
//        assertThat(stock.getAmountOf("balls mozzarella")).isEqualTo(4); // before 20, after 4
//        assertThat(stock.getAmountOf("tomatoes")).isEqualTo(0); // before 20, after 0
//        assertThat(stock.getAmountOf("olive oil")).isEqualTo(UNLIMITED_AMOUNT); // before : unlimited, after: unlimited
//    }
}
