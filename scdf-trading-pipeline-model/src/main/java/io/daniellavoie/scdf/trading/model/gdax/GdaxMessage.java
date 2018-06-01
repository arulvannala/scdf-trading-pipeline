package io.daniellavoie.scdf.trading.model.gdax;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class GdaxMessage {
	private final MessageType messageType;
	private final LocalDateTime time;
	private final Long tradeId;
	private final Long lastTradeId;
	private final String productId;
	private final String[] productIds;
	private final Long sequence;
	private final BookEntry[] asks;
	private final BookEntry[] bids;
	private final String makerOrderId;
	private final String takerOrderId;
	private final String orderId;
	private final Double size;
	private final Double newSize;
	private final Double oldSize;
	private final Double price;
	private final String reason;
	private final Double remainingSize;
	private final Double funds;
	private final Double oldFunds;
	private final Double newFunds;
	private final Side side;
	private final OrderType orderType;
	private final String message;
	private final Double open24h;
	private final Double volume24h;
	private final Double low24h;
	private final Double high24h;
	private final Double volume30d;
	private final Double bestBid;
	private final Double bestAsk;

	@JsonCreator
	public GdaxMessage(@JsonProperty("type") MessageType messageType, @JsonProperty("time") LocalDateTime time,
			@JsonProperty("trade_id") Long tradeId, @JsonProperty("last_trade_id") Long lastTradeId,
			@JsonProperty("product_id") String productId, @JsonProperty("product_ids") String[] productIds,
			@JsonProperty("sequence") Long sequence, @JsonProperty("asks") BookEntry[] asks,
			@JsonProperty("bids") BookEntry[] bids, @JsonProperty("maker_order_id") String makerOrderId,
			@JsonProperty("taker_order_id") String takerOrderId, @JsonProperty("order_id") String orderId,
			@JsonProperty("size") Double size, @JsonProperty("new_size") Double newSize,
			@JsonProperty("old_size") Double oldSize, @JsonProperty("price") Double price,
			@JsonProperty("reason") String reason, @JsonProperty("remaining_size") Double remainingSize,
			@JsonProperty("funds") Double funds, @JsonProperty("old_funds") Double oldFunds,
			@JsonProperty("new_funds") Double newFunds, @JsonProperty("side") Side side,
			@JsonProperty("order_type") OrderType orderType, @JsonProperty("message") String message,
			@JsonProperty("open_24h") final Double open24h, @JsonProperty("volume_24h") final Double volume24h,
			@JsonProperty("low_24h") final Double low24h, @JsonProperty("high_24h") final Double high24h,
			@JsonProperty("volume_30d") final Double volume30d, @JsonProperty("best_bid") Double bestBid,
			@JsonProperty("best_ask") final Double bestAsk) {
		this.messageType = messageType;
		this.time = time;
		this.tradeId = tradeId;
		this.lastTradeId = lastTradeId;
		this.productId = productId;
		this.productIds = productIds;
		this.sequence = sequence;
		this.asks = asks;
		this.bids = bids;
		this.makerOrderId = makerOrderId;
		this.takerOrderId = takerOrderId;
		this.orderId = orderId;
		this.price = price;
		this.reason = reason;
		this.size = size;
		this.oldSize = oldSize;
		this.newSize = newSize;
		this.remainingSize = remainingSize;
		this.funds = funds;
		this.oldFunds = oldFunds;
		this.newFunds = newFunds;
		this.side = side;
		this.orderType = orderType;
		this.message = message;
		this.open24h = open24h;
		this.volume24h = volume24h;
		this.low24h = low24h;
		this.high24h = high24h;
		this.volume30d = volume30d;
		this.bestBid = bestBid;
		this.bestAsk = bestAsk;
	}

	public MessageType getMessageType() {
		return messageType;
	}

	public LocalDateTime getTime() {
		return time;
	}

	public Long getTradeId() {
		return tradeId;
	}

	public Long getLastTradeId() {
		return lastTradeId;
	}

	public String getProductId() {
		return productId;
	}

	public String[] getProductIds() {
		return productIds;
	}

	public Long getSequence() {
		return sequence;
	}

	public BookEntry[] getAsks() {
		return asks;
	}

	public BookEntry[] getBids() {
		return bids;
	}

	public String getMakerOrderId() {
		return makerOrderId;
	}

	public String getTakerOrderId() {
		return takerOrderId;
	}

	public String getOrderId() {
		return orderId;
	}

	public Double getSize() {
		return size;
	}

	public Double getNewSize() {
		return newSize;
	}

	public Double getOldSize() {
		return oldSize;
	}

	public Double getPrice() {
		return price;
	}

	public String getReason() {
		return reason;
	}

	public Double getRemainingSize() {
		return remainingSize;
	}

	public Double getFunds() {
		return funds;
	}

	public Double getOldFunds() {
		return oldFunds;
	}

	public Double getNewFunds() {
		return newFunds;
	}

	public Side getSide() {
		return side;
	}

	public OrderType getOrderType() {
		return orderType;
	}

	public String getMessage() {
		return message;
	}

	public Double getOpen24h() {
		return open24h;
	}

	public Double getVolume24h() {
		return volume24h;
	}

	public Double getLow24h() {
		return low24h;
	}

	public Double getHigh24h() {
		return high24h;
	}

	public Double getVolume30d() {
		return volume30d;
	}

	public Double getBestBid() {
		return bestBid;
	}

	public Double getBestAsk() {
		return bestAsk;
	}
}
