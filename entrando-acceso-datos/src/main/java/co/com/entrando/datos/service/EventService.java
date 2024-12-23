package co.com.entrando.datos.service;

import co.com.entrando.datos.entity.Event;
import co.com.entrando.enumeration.EventStatus;

import java.util.Optional;
import java.util.Set;

public interface EventService {
    Event save(Event event);
    Optional<Event> findById(Long id);
    Set<Event> getAll();
    Boolean updateEventStatus(Long id, EventStatus eventStatus);
}
