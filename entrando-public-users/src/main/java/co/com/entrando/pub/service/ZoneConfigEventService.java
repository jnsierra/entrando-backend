package co.com.entrando.pub.service;

import co.com.entrando.datos.entity.ZoneConfigEvent;

import java.util.Set;

public interface ZoneConfigEventService {
    Set<ZoneConfigEvent> getByIdEventAndPresentId(Long idEvent, Long idPresent);
}
