package com.sroui.restaurant.meal;


import java.util.Objects;

public class Ticket {
	private static Integer counter = -1;
	private final Integer id;

	public Ticket() {
		this.id = ++counter;
	}

	public Ticket and(String string) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Ticket ticket = (Ticket) o;
		return Objects.equals(id, ticket.id);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}
}
