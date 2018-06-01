package io.daniellavoie.scdf.trading.event.model;

import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.stereotype.Service;

@Service
public class EventProcessor {
	private EventService eventService;

	public EventProcessor(EventService eventService) {
		this.eventService = eventService;
	}

	@Service
	@EnableBinding(TickSink.class)
	public class TickProcessor {
		@StreamListener(value = TickSink.CHANNEL)
		public void processTickEvent(String payload) {
			eventService.saveNewEvent("tick", payload);
		}
	}

	@Service
	@EnableBinding(PortfolioPricingSink.class)
	public class PortfolioPricingProcessor {
		@StreamListener(value = PortfolioPricingSink.CHANNEL)
		public void processTickEvent(String payload) {
			eventService.saveNewEvent("portfolio-pricing", payload);
		}
	}
}
