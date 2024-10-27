package co.com.entrando.datos.service;

import co.com.entrando.datos.entity.ConfigEvent;

import java.util.Optional;
import java.util.Set;

public interface ConfigEventService {
    ConfigEvent save(ConfigEvent entity);
    Optional<ConfigEvent> findById(Long id);
    Set<ConfigEvent> getAll();
    Set<ConfigEvent> findByEventId(Long eventId);
    Optional<Long> recordSale(Long eventId, Long presentationId);
    Optional<ConfigEvent> findByEventIdAndPresentationId(Long eventId, Long presentationId);
}
