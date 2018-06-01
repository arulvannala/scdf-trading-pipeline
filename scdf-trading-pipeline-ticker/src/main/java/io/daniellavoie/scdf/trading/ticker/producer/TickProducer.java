package io.daniellavoie.scdf.trading.ticker.producer;

import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

import io.daniellavoie.scdf.trading.model.gdax.Products;
import io.daniellavoie.scdf.trading.model.gdax.SubscribeRequest;
import io.daniellavoie.scdf.trading.ticker.gdax.ChannelService;
import reactor.core.Disposable;

@Service
@EnableBinding(TickSource.class)
public class TickProducer {
	private static final Logger LOGGER = LoggerFactory.getLogger(TickProducer.class);
	private TickSource tickSource;
	private ChannelService channelService;

	private Disposable subscription;

	public TickProducer(TickSource tickSource, ChannelService channelService) {
		this.tickSource = tickSource;
		this.channelService = channelService;
	}

	@EventListener
	public void applicationStarted(ContextRefreshedEvent contextRefreshedEvent) {
		if (subscription == null || subscription.isDisposed()) {
			subscription = channelService.subscribe(new SubscribeRequest(Products.asList(), Arrays.asList("ticker")))

					.doOnNext(message -> LOGGER.info("Notifying tick for product " + message.getProductId() + "."))

					.doOnNext(message -> tickSource.output().send(MessageBuilder.withPayload(message).build()))

					.subscribe();
		}
	}
}