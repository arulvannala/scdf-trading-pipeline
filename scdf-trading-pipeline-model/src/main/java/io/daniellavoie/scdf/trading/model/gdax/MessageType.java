package io.daniellavoie.scdf.trading.model.gdax;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum MessageType {
	@JsonProperty("change")
	CHANGE,

	@JsonProperty("done")
	DONE,

	@JsonProperty("error")
	ERROR,

	@JsonProperty("heartbeat")
	HEARTBEAT,

	@JsonProperty("match")
	MATCH,

	@JsonProperty("open")
	OPEN,

	@JsonProperty("received")
	RECEIVED,

	@JsonProperty("subscribe")
	SUBSCRIBE,

	@JsonProperty("ticker")
	TICKER,

	@JsonProperty("subscriptions")
	SUBSCRIPTIONS;

}