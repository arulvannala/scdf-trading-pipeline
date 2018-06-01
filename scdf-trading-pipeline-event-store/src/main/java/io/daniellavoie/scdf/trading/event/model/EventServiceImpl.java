package io.daniellavoie.scdf.trading.event.model;

import java.time.LocalDateTime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class EventServiceImpl implements EventService {
	private static final Logger LOGGER = LoggerFactory.getLogger(EventServiceImpl.class);

	private EventRepository eventRepository;

	public EventServiceImpl(EventRepository eventRepository) {
		this.eventRepository = eventRepository;
	}

	@Override
	public Page<Event> findAll(Pageable pageable) {
		return findAll(pageable);
	}

	@Override
	public Event saveNewEvent(String type, String payload) {
		Event event = new Event(null, LocalDateTime.now(), type, payload);

		LOGGER.info("Persisting " + event);

		return eventRepository.save(event);
	}
}
