package io.daniellavoie.scdf.trading.model.gdax;

import java.util.List;

public class SubscribeRequest {
	private final String type;
	private final List<String> productIds;
	private final List<String> channels;

	public SubscribeRequest(final List<String> productIds, final List<String> channels) {
		this.type = "subscribe";
		this.productIds = productIds;
		this.channels = channels;
	}

	public String getType() {
		return type;
	}

	public List<String> getProductIds() {
		return productIds;
	}

	public List<String> getChannels() {
		return channels;
	}
}
