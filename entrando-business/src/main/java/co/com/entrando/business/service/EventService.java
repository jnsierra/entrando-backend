package co.com.entrando.business.service;

import co.com.entrando.dto.EventDto;

import java.util.Optional;

public interface EventService {
    Boolean changeStatusEventWaiting(Long idEvent);
    Optional<EventDto> getEventById(Long id);
}