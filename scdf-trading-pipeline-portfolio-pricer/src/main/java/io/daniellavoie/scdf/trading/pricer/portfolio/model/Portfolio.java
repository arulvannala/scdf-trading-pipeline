package io.daniellavoie.scdf.trading.pricer.portfolio.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Portfolio {
	private final long id;
	private final long ownerId;
	private final List<PortfolioProduct> portfolioProducts;

	@JsonCreator
	public Portfolio(@JsonProperty("id") final long id, @JsonProperty("ownerId") final long ownerId,
			@JsonProperty("portfolioProducts") final List<PortfolioProduct> portfolioProducts) {
		this.id = id;
		this.ownerId = ownerId;
		this.portfolioProducts = portfolioProducts;
	}

	public long getId() {
		return id;
	}

	public long getOwnerId() {
		return ownerId;
	}

	public List<PortfolioProduct> getPortfolioProducts() {
		return portfolioProducts;
	}
}
