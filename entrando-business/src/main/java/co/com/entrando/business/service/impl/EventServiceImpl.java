package co.com.entrando.business.service.impl;

import co.com.entrando.business.client.EventClient;
import co.com.entrando.business.service.EventService;
import co.com.entrando.dto.EventDto;
import co.com.entrando.enumeration.EventStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EventServiceImpl implements EventService {
    private final EventClient eventClient;
    @Autowired
    public EventServiceImpl(EventClient eventClient) {
        this.eventClient = eventClient;
    }
    @Override
    public Boolean changeStatusEventWaiting(Long idEvent) {
        return eventClient.updateStatusEvent(idEvent, EventStatus.WAITING);
    }
    @Override
    public Optional<EventDto> getEventById(Long id) {
        return Optional.ofNullable(eventClient.get(id));
    }
}