package io.daniellavoie.scdf.trading.pricer.portfolio;

import io.daniellavoie.scdf.trading.pricer.portfolio.model.Portfolio;
import reactor.core.publisher.Flux;

public interface PortfolioService {
	Flux<Portfolio> findAll();
}
