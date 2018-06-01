package io.daniellavoie.scdf.trading.ticker.gdax;

import java.io.IOException;
import java.net.URI;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.socket.WebSocketMessage;
import org.springframework.web.reactive.socket.client.ReactorNettyWebSocketClient;
import org.springframework.web.reactive.socket.client.WebSocketClient;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;

import io.daniellavoie.scdf.trading.model.gdax.GdaxMessage;
import io.daniellavoie.scdf.trading.model.gdax.SubscribeRequest;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ChannelServiceImpl implements ChannelService {
	private final String channelWsUri;

	private final WebSocketClient client = new ReactorNettyWebSocketClient();
	private final ObjectMapper objectMapper = new ObjectMapper().findAndRegisterModules()
			.setPropertyNamingStrategy(PropertyNamingStrategy.SnakeCaseStrategy.SNAKE_CASE)
			.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

	public ChannelServiceImpl(@Value("${gdax.channel.uri}") final String channelWsUri) {
		this.channelWsUri = channelWsUri;
	}

	public Flux<GdaxMessage> subscribe(SubscribeRequest subscribeRequest) {
		return Flux.create(subscriber -> {
			client.execute(URI.create(channelWsUri),

					// Send the subscribe message.
					session -> {
						return session.send(Mono.just(session.textMessage(textPayload(subscribeRequest))))

								// Transform responses to a DTO
								.thenMany(session.receive().map(WebSocketMessage::getPayload).map(this::readPayload))

								// Notify each responses
								.doOnNext(payload -> subscriber.next(payload))

								.then();
					}).doOnError(e -> subscriber.error(e))

					.subscribe();
		});
	}

	private String textPayload(SubscribeRequest subscribeRequest) {
		try {
			return objectMapper.writeValueAsString(subscribeRequest);
		} catch (JsonProcessingException e) {
			throw new RuntimeException(e);
		}
	}

	private GdaxMessage readPayload(DataBuffer payload) {
		try {
			return objectMapper.readValue(payload.asInputStream(), GdaxMessage.class);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
}
