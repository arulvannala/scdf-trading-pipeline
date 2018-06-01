package io.daniellavoie.scdf.trading.portfolio;

import org.springframework.data.jpa.repository.JpaRepository;

import io.daniellavoie.scdf.trading.portfolio.model.Portfolio;

public interface PortfolioRepository extends JpaRepository<Portfolio, Long> {

}
