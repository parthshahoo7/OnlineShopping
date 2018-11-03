package edu.shah.model;

import java.util.ArrayList;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope(value = "session")
public class ShoppingCart {
	private ArrayList<LineItem> lineItems;
	private int quantity;

	public ShoppingCart() {
		super();
		// TODO Auto-generated constructor stub
		lineItems = new ArrayList<LineItem>();
	}

	public void addLineItems(LineItem lineItem) {
		lineItems.add(lineItem);
	}

	public ArrayList<LineItem> getLineItems() {
		return lineItems;
	}

	public void setLineItems(ArrayList<LineItem> lineItems) {
		this.lineItems = lineItems;
	}

	public void clear() {
		lineItems.clear();
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
}