package io.daniellavoie.scdf.trading.model.gdax;

import com.fasterxml.jackson.annotation.JsonCreator;

public class BookEntry {
	private double price;
	private double size;
	private String orderId;

	@JsonCreator
	public BookEntry(String[] properties) {
		this.price = Double.valueOf(properties[0]);
		this.size = Double.valueOf(properties[1]);
		this.orderId = properties[2];
	}

	public BookEntry(double price, double size, String orderId) {
		this.price = price;
		this.size = size;
		this.orderId = orderId;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public double getSize() {
		return size;
	}

	public void setSize(double size) {
		this.size = size;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
}
