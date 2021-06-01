package com.sroui.stock.exceptions;

public class NotEnoughItemAmountInStockException extends Exception{
    public NotEnoughItemAmountInStockException(String message) {
        super(message);
    }
}
