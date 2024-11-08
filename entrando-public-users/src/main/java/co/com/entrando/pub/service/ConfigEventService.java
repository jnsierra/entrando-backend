package co.com.entrando.pub.service;


import co.com.entrando.datos.entity.ConfigEvent;

import java.util.Optional;

public interface ConfigEventService {

    Optional<ConfigEvent> findByEventIdAndPresentationId(Long idEvent, Long idPresentation);
}
