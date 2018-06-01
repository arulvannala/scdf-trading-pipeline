package io.daniellavoie.scdf.trading.event.model;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.MessageChannel;

public interface TickSink {
	String CHANNEL = "pricer-tick";

	@Input(TickSink.CHANNEL)
	MessageChannel input();
}