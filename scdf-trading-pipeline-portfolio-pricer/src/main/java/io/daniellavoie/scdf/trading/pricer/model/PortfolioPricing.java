package io.daniellavoie.scdf.trading.pricer.model;

import java.util.List;

import io.daniellavoie.scdf.trading.pricer.portfolio.model.Portfolio;

public class PortfolioPricing {
	private final Portfolio portfolio;
	private final List<ProductPricing> productPricings;

	public PortfolioPricing(final Portfolio portfolio, final List<ProductPricing> productPricings) {
		this.portfolio = portfolio;
		this.productPricings = productPricings;
	}

	public Portfolio getPortfolio() {
		return portfolio;
	}

	public List<ProductPricing> getProductPricings() {
		return productPricings;
	}
}
