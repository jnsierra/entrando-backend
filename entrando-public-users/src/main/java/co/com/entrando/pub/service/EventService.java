package co.com.entrando.pub.service;

import co.com.entrando.datos.entity.Event;

import java.util.Optional;
import java.util.Set;

public interface EventService {
    Set<Event> getAllActive();
    Set<Event> getActiveAndAfterToday();
    Optional<Event> getById(Long id);

    Optional<Event> getByIdAndPresentationId(Long id, Long idPresentation);
}
