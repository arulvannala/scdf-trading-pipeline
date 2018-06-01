package io.daniellavoie.scdf.trading.event.model;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.MessageChannel;

public interface PortfolioPricingSink {
	String CHANNEL = "portfolio-pricing";

	@Input(PortfolioPricingSink.CHANNEL)
	MessageChannel input();
}