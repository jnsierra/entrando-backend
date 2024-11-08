package co.com.entrando.pub.service.impl;

import co.com.entrando.datos.entity.Zone;
import co.com.entrando.datos.repository.ZoneRepository;
import co.com.entrando.pub.service.ZoneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class ZoneServiceImpl implements ZoneService {
    private final ZoneRepository zoneRepository;
    @Autowired
    public ZoneServiceImpl(ZoneRepository zoneRepository) {
        this.zoneRepository = zoneRepository;
    }
    @Override
    public Set<Zone> getByCategory(Long idCategory) {
        return zoneRepository.findByCategory(idCategory);
    }
}
