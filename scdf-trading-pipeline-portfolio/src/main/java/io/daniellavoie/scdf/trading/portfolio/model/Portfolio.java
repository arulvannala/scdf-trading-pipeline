package io.daniellavoie.scdf.trading.portfolio.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.hibernate.annotations.GenericGenerator;

@Entity
public class Portfolio {
	private long id;
	private long ownerId;
	private List<PortfolioProduct> portfolioProducts;

	public Portfolio() {

	}

	public Portfolio(long id, long ownerId, List<PortfolioProduct> portfolioProducts) {
		this.id = id;
		this.ownerId = ownerId;
		this.portfolioProducts = portfolioProducts;
	}

	@Id
	@GenericGenerator(name = "native", strategy = "native")
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getOwnerId() {
		return ownerId;
	}

	public void setOwnerId(long ownerId) {
		this.ownerId = ownerId;
	}

	@OneToMany(mappedBy = "portfolio")
	public List<PortfolioProduct> getPortfolioProducts() {
		return portfolioProducts;
	}

	public void setPortfolioProducts(List<PortfolioProduct> portfolioProducts) {
		this.portfolioProducts = portfolioProducts;
	}
}
