package io.daniellavoie.scdf.trading.pricer.producer;

import io.daniellavoie.scdf.trading.pricer.model.PortfolioPricing;

public interface PortfolioPricingProducer {
	void notifyPortfolio(PortfolioPricing portfolioPricing);
}
