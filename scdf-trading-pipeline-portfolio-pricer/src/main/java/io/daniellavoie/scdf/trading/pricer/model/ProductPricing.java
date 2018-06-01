package io.daniellavoie.scdf.trading.pricer.model;

public class ProductPricing {
	private final String productId;
	private final double productValue;

	public ProductPricing(final String productId, final double productValue) {
		this.productId = productId;
		this.productValue = productValue;
	}

	public String getProductId() {
		return productId;
	}

	public double getProductValue() {
		return productValue;
	}
}
