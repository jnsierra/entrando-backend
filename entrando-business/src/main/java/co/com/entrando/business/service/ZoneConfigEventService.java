package co.com.entrando.business.service;


import co.com.entrando.dto.ZoneConfigEventDto;

import java.util.Set;

public interface ZoneConfigEventService {

    Set<ZoneConfigEventDto>  getByIdEvent(Long idEvent);
    Set<ZoneConfigEventDto>  getByIdEventAndPresentation(Long idEvent, Long idPresentation);
    ZoneConfigEventDto save(ZoneConfigEventDto zoneConfigEventDto);
    Boolean updateCreateTickets(Long id);
}
