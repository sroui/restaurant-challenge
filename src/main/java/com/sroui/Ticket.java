package com.sroui;


public class Ticket {
	private final String recipeName;
	private final int servedDishes;

	public Ticket(String recipeName, int servedDishes) {
		this.servedDishes = servedDishes;
		this.recipeName = recipeName;
	}

	public Ticket and(String string) {
		// TODO Auto-generated method stub
		return null;
	}

	public String getRecipeName() {
		return recipeName;
	}

	public int getServedDishes() {
		return servedDishes;
	}
}
