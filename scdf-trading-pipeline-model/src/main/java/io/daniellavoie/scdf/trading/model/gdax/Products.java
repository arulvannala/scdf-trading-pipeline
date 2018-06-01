package io.daniellavoie.scdf.trading.model.gdax;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public enum Products {
	BCH_BTC("BCH-BTC"),

	BCH_USD("BCH-USD"),

	BTC_EUR("BTC-EUR"),

	BTC_GBP("BTC-GBP"),

	BTC_USD("BTC-USD"),

	ETH_BTC("ETH-BTC"),

	ETH_USD("ETH-USD"),

	LTC_BTC("LTC-BTC"),

	LTC_USD("LTC-USD"),

	BCH_EUR("BCH-EUR");

	private final String key;

	Products(String key) {
		this.key = key;
	}

	public String getKey() {
		return key;
	}

	public static final List<String> asList() {
		return Arrays.stream(Products.values()).map(Products::getKey).collect(Collectors.toList());
	}
}
