package io.daniellavoie.scdf.trading.model.gdax;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum OrderType {
	@JsonProperty("limit")
	LIMIT,

	@JsonProperty("market")
	MARKET;
}