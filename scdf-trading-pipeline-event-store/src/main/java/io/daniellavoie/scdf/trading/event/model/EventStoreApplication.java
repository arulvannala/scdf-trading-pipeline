package io.daniellavoie.scdf.trading.event.model;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class EventStoreApplication {
	public static final void main(String[] args) {
		SpringApplication.run(EventStoreApplication.class, args);
	}
}
