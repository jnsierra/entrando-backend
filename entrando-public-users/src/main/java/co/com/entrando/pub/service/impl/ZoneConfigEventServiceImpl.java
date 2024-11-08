package co.com.entrando.pub.service.impl;

import co.com.entrando.datos.entity.ZoneConfigEvent;
import co.com.entrando.datos.repository.ZoneConfigEventRepository;
import co.com.entrando.pub.service.ZoneConfigEventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class ZoneConfigEventServiceImpl implements ZoneConfigEventService {
    private ZoneConfigEventRepository zoneConfigEventRepository;
    @Autowired
    public ZoneConfigEventServiceImpl(ZoneConfigEventRepository zoneConfigEventRepository) {
        this.zoneConfigEventRepository = zoneConfigEventRepository;
    }
    @Override
    public Set<ZoneConfigEvent> getByIdEventAndPresentId(Long idEvent, Long idPresent) {
        return this.zoneConfigEventRepository.getZoneConfigByEventAndPresentation(idEvent, idPresent);
    }
}
