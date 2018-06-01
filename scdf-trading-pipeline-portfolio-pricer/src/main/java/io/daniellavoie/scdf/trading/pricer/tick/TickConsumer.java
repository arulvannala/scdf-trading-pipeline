package io.daniellavoie.scdf.trading.pricer.tick;

import io.daniellavoie.scdf.trading.model.gdax.GdaxMessage;
import reactor.core.publisher.Flux;

public interface TickConsumer {
	Flux<GdaxMessage> getTicks();
}
