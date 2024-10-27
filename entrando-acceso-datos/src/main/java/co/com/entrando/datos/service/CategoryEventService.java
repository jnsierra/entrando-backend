package co.com.entrando.datos.service;

import co.com.entrando.datos.entity.EventCategory;

import java.util.Optional;
import java.util.Set;

public interface CategoryEventService {

    EventCategory save(EventCategory event);
    Set<EventCategory> findAll();
    Optional<EventCategory> findById(Long id);
}
