package io.daniellavoie.scdf.trading.portfolio.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
public class PortfolioProduct {
	private long id;

	@JsonBackReference
	private Portfolio portfolio;

	private String productId;
	private double amount;

	public PortfolioProduct() {

	}

	public PortfolioProduct(long id, Portfolio portfolio, String productId, double amount) {
		this.id = id;
		this.portfolio = portfolio;
		this.productId = productId;
		this.amount = amount;
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

	@ManyToOne()
	public Portfolio getPortfolio() {
		return portfolio;
	}

	public void setPortfolio(Portfolio portfolio) {
		this.portfolio = portfolio;
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}
}
