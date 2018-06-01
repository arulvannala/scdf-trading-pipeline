package io.daniellavoie.scdf.trading.model.gdax;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum Side {
	@JsonProperty("buy")
	BUY,

	@JsonProperty("sell")
	SELL;
}
