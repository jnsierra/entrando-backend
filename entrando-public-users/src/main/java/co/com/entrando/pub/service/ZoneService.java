package co.com.entrando.pub.service;


import co.com.entrando.datos.entity.Zone;

import java.util.Set;

public interface ZoneService {
    Set<Zone> getByCategory(Long idCategory);
}