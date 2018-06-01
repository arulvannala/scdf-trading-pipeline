package io.daniellavoie.scdf.trading.pricer.portfolio;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import io.daniellavoie.scdf.trading.pricer.portfolio.model.Portfolio;
import reactor.core.publisher.Flux;

@Service
public class PortfolioServiceImpl implements PortfolioService {
	private final WebClient webClient;

	public PortfolioServiceImpl(@Value("${portfolio.uri}") String portfolioUri) {
		this.webClient = WebClient.create(portfolioUri);
	}

	@Override
	public Flux<Portfolio> findAll() {
		return webClient.get().uri("/portfolio").retrieve().bodyToFlux(Portfolio.class);
	}

}
