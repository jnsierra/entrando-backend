package co.com.entrando.business.service.impl;

import co.com.entrando.business.client.ZoneConfigEventClient;
import co.com.entrando.business.service.ZoneConfigEventService;
import co.com.entrando.dto.ZoneConfigEventDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class ZoneConfigEventServiceImpl implements ZoneConfigEventService {
    private final ZoneConfigEventClient zoneConfigEventClient;
    @Autowired
    public ZoneConfigEventServiceImpl(ZoneConfigEventClient zoneConfigEventClient) {
        this.zoneConfigEventClient = zoneConfigEventClient;
    }
    @Override
    public Set<ZoneConfigEventDto> getByIdEvent(Long idEvent) {
        return zoneConfigEventClient.getByIdEvent(idEvent);
    }

    @Override
    public Set<ZoneConfigEventDto> getByIdEventAndPresentation(Long idEvent, Long idPresentation) {
        return zoneConfigEventClient.getByIdEventAndPresentation(idEvent, idPresentation);
    }
    public ZoneConfigEventDto save(ZoneConfigEventDto zoneConfigEventDto){
        return zoneConfigEventClient.save(zoneConfigEventDto);
    }
    @Override
    public Boolean updateCreateTickets(Long id) {
        return zoneConfigEventClient.updateCreateTickets(id);
    }
}
