package co.com.entrando.datos.service;

import co.com.entrando.datos.entity.ZoneConfigEvent;

import java.util.Optional;
import java.util.Set;

public interface ZoneConfigEventService {
    ZoneConfigEvent save(ZoneConfigEvent zoneConfigEvent);

    Optional<ZoneConfigEvent> getById(Long id);

    Set<ZoneConfigEvent> getZoneConfigByEvent(Long id);

    Set<ZoneConfigEvent> getZoneConfigByEventAndPresentation(Long idEvent, Long idPresentation);

    Boolean recordSale(Long zoneId, Long configEventId);

    Boolean updateCreateTickets(Long id);
}