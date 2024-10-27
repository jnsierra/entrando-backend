package co.com.entrando.datos.service.impl;

import co.com.entrando.datos.entity.Zone;
import co.com.entrando.datos.repository.ZoneRepository;
import co.com.entrando.datos.service.ZoneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class ZoneServiceImpl implements ZoneService {
    private ZoneRepository zoneRepository;

    @Autowired
    public ZoneServiceImpl(ZoneRepository zoneRepository) {
        this.zoneRepository = zoneRepository;
    }

    @Override
    public Zone save(Zone zone) {
        return zoneRepository.save(zone);
    }

    @Override
    public Optional<Zone> getById(Long id) {
        return zoneRepository.findById(id);
    }

    @Override
    public Set<Zone> getAll() {
        return new HashSet<>(zoneRepository.findAll());
    }
}
