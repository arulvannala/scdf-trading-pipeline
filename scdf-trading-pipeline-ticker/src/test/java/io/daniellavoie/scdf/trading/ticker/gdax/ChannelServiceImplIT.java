package io.daniellavoie.scdf.trading.ticker.gdax;

import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import io.daniellavoie.scdf.trading.model.gdax.GdaxMessage;
import io.daniellavoie.scdf.trading.model.gdax.Products;
import io.daniellavoie.scdf.trading.model.gdax.SubscribeRequest;

@SpringBootTest
@RunWith(SpringRunner.class)
public class ChannelServiceImplIT {
	private static final Logger logger = LoggerFactory.getLogger(ChannelServiceImplIT.class);

	@Autowired
	private ChannelService channelService;

	@Test
	public void test() {
		List<GdaxMessage> messages = channelService
				.subscribe(new SubscribeRequest(Products.asList(), Arrays.asList("ticker")))
				.doOnNext(message -> logger.info("Product : " + message.getProductId() + " | Bid : "
						+ message.getBestBid() + " - " + message.getBestAsk() + " : Ask"))
				.buffer(5).blockFirst();

		Assert.assertEquals(5, messages.size());
	}
}
