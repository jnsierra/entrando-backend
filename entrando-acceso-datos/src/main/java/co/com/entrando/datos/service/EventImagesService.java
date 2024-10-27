package co.com.entrando.datos.service;

import co.com.entrando.datos.entity.EventImage;

import java.util.Optional;
import java.util.Set;

public interface EventImagesService {
    EventImage save(EventImage eventImage);
    Set<EventImage> findAll();
    Optional<EventImage> findById(Long id);
    Set<EventImage> findByEvent(Long idEvent);
}
