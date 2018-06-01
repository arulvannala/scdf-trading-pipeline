package io.daniellavoie.scdf.trading.ticker.producer;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

public interface TickSource {
	String TICK = "pricer-tick";

	@Output(TickSource.TICK)
	MessageChannel output();
}
