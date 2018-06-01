package io.daniellavoie.scdf.trading.portfolio;

import java.util.List;

import org.springframework.stereotype.Service;

import io.daniellavoie.scdf.trading.portfolio.model.Portfolio;

@Service
public class PortfolioServiceImpl implements PortfolioService {
	private PortfolioRepository portfolioRepository;

	public PortfolioServiceImpl(PortfolioRepository portfolioRepository) {
		this.portfolioRepository = portfolioRepository;
	}

	@Override
	public List<Portfolio> findAll() {
		return portfolioRepository.findAll();
	}

}
