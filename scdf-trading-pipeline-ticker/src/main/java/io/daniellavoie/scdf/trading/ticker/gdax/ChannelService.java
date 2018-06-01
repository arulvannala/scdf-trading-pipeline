package io.daniellavoie.scdf.trading.ticker.gdax;

import io.daniellavoie.scdf.trading.model.gdax.GdaxMessage;
import io.daniellavoie.scdf.trading.model.gdax.SubscribeRequest;
import reactor.core.publisher.Flux;

public interface ChannelService {
	Flux<GdaxMessage> subscribe(SubscribeRequest subscribeRequest);
}
