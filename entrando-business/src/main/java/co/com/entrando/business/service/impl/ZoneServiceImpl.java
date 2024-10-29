package co.com.entrando.business.service.impl;

import co.com.entrando.business.client.ZoneClient;
import co.com.entrando.business.service.ZoneService;
import co.com.entrando.dto.ZoneDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ZoneServiceImpl implements ZoneService {
    private final ZoneClient zoneClient;
    @Autowired
    public ZoneServiceImpl(ZoneClient zoneClient) {
        this.zoneClient = zoneClient;
    }
    @Override
    public ZoneDto getById(Long idZone) {
        return zoneClient.getById(idZone);
    }
}
