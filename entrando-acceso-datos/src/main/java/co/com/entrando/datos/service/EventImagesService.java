package co.com.entrando.datos.service;

import co.com.entrando.datos.entity.EventImages;

import java.util.Optional;
import java.util.Set;

public interface EventImagesService {
    EventImages save(EventImages eventImage);
    Set<EventImages> findAll();
    Optional<EventImages> findById(Long id);
    Set<EventImages> findByEvent(Long idEvent);
}
