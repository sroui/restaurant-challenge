package com.sroui;


import static com.sroui.stock.StockItem.UNLIMITED_AMOUNT;

public class Ticket {
	private final String recipeName;
	private final int dishCount;

	public Ticket(String recipeName, int servedDishes) {
		this.dishCount = servedDishes;
		this.recipeName = recipeName;
	}

	public Ticket and(String string) {
		// TODO Auto-generated method stub
		return null;
	}

	public String getRecipeName() {
		return recipeName;
	}

	public int getDishCount() {
		return dishCount;
	}

	public static Ticket parseTicketFromOrder(String order) {
		String[] orderSplits = order.split(" ",2);

		int servedDishes;
		String orderedRecipeName;

		if (orderSplits.length == 2) {
			servedDishes = Integer.parseInt(orderSplits[0]);
			orderedRecipeName = orderSplits[1];
		} else {
			servedDishes = UNLIMITED_AMOUNT;
			orderedRecipeName = order;
		}

		return new Ticket(orderedRecipeName, servedDishes);
	}
}
