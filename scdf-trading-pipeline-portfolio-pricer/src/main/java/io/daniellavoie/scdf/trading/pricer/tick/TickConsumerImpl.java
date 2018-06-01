package io.daniellavoie.scdf.trading.pricer.tick;

import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.stereotype.Service;

import io.daniellavoie.scdf.trading.model.gdax.GdaxMessage;
import reactor.core.publisher.DirectProcessor;
import reactor.core.publisher.Flux;

@Service
@EnableBinding(TickSink.class)
public class TickConsumerImpl implements TickConsumer {
	private final DirectProcessor<GdaxMessage> processor = DirectProcessor.create();
	private final Flux<GdaxMessage> flux = processor.onBackpressureBuffer();

	@StreamListener(value = TickSink.CHANNEL)
	public void handle(GdaxMessage gdaxMessage) {
		processor.onNext(gdaxMessage);
	}

	@Override
	public Flux<GdaxMessage> getTicks() {
		return flux;
	}
}
