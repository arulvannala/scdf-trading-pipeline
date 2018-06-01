package io.daniellavoie.scdf.trading.portfolio;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.daniellavoie.scdf.trading.portfolio.model.Portfolio;

@RestController
@RequestMapping("/portfolio")
public class PortfolioController {
	private PortfolioService portfolioService;

	public PortfolioController(PortfolioService portfolioService) {
		this.portfolioService = portfolioService;
	}

	@GetMapping
	public List<Portfolio> findAll() {
		return portfolioService.findAll();
	}
}
