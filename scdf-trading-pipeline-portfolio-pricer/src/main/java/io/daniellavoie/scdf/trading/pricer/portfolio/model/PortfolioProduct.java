package io.daniellavoie.scdf.trading.pricer.portfolio.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class PortfolioProduct {
	private final long id;
	private final String productId;
	private final double amount;

	@JsonCreator
	public PortfolioProduct(@JsonProperty("id") final long id, @JsonProperty("product_id") final String productId,
			@JsonProperty("amount") final double amount) {
		this.id = id;
		this.productId = productId;
		this.amount = amount;
	}

	public long getId() {
		return id;
	}

	public String getProductId() {
		return productId;
	}

	public double getAmount() {
		return amount;
	}
}
