package co.com.entrando.datos.service;

import co.com.entrando.datos.entity.Zone;

import java.util.Optional;
import java.util.Set;

public interface ZoneService {
    Zone save(Zone zone);
    Optional<Zone> getById(Long id);
    Set<Zone> getAll();
}
