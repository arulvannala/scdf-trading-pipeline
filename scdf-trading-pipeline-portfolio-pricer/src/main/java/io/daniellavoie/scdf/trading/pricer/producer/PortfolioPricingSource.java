package io.daniellavoie.scdf.trading.pricer.producer;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

public interface PortfolioPricingSource {
	String CHANNEL = "portfolio-pricing";

	@Output(PortfolioPricingSource.CHANNEL)
	MessageChannel output();
}
