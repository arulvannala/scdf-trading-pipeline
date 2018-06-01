package io.daniellavoie.scdf.trading.event.model;

import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;

public class Event {
	@Id
	private String id;

	private LocalDateTime timestamp;
	private String type;
	private String payload;

	public Event() {

	}

	public Event(String id, LocalDateTime timestamp, String type, String payload) {
		this.id = id;
		this.timestamp = timestamp;
		this.type = type;
		this.payload = payload;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public LocalDateTime getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(LocalDateTime timestamp) {
		this.timestamp = timestamp;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getPayload() {
		return payload;
	}

	public void setPayload(String payload) {
		this.payload = payload;
	}

	@Override
	public String toString() {
		return "Event [id=" + id + ", timestamp=" + timestamp + ", type=" + type + ", payload=" + payload + "]";
	}
}
