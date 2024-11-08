package co.com.entrando.pub.service;


import co.com.entrando.datos.entity.Presentation;

import java.util.Set;

public interface PresentationService {
    Set<Presentation> findByEvent(Long eventId);
}