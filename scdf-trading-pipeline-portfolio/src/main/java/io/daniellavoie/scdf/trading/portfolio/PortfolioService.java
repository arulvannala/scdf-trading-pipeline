package io.daniellavoie.scdf.trading.portfolio;

import java.util.List;

import io.daniellavoie.scdf.trading.portfolio.model.Portfolio;

public interface PortfolioService {
	List<Portfolio> findAll();
}
