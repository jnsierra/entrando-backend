package co.com.entrando.datos.service;

import co.com.entrando.datos.entity.Presentation;

import java.util.Optional;
import java.util.Set;

public interface PresentationService {

    Presentation save(Presentation presentation);
    Optional<Presentation> getById(Long id);
    Set<Presentation> getAll();
    Set<Presentation> findByEvent(Long eventId);
}
