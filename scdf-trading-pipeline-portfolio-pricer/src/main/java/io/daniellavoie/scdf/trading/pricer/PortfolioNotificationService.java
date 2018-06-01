package io.daniellavoie.scdf.trading.pricer;

import io.daniellavoie.scdf.trading.pricer.model.PortfolioPricing;

public interface PortfolioNotificationService {
	public void notifyPortfolio(PortfolioPricing portfolioPricing);
}
