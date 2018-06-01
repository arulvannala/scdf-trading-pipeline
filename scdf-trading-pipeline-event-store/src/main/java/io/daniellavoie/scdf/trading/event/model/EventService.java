package io.daniellavoie.scdf.trading.event.model;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface EventService {
	Event saveNewEvent(String type, String payload);

	Page<Event> findAll(Pageable pageable);
}
