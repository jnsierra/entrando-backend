package co.com.entrando.pub.service.impl;

import co.com.entrando.datos.entity.Event;
import co.com.entrando.datos.repository.EventRepository;
import co.com.entrando.pub.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import co.com.entrando.enumeration.EventStatus;

import java.util.Optional;
import java.util.Set;

@Service
public class EventServiceImpl implements EventService {
    private final EventRepository eventRepository;
    @Autowired
    public EventServiceImpl(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }
    @Override
    public Set<Event> getAllActive() {
        return eventRepository.findByEventStatus(EventStatus.CREATED.toString());
    }
    @Override
    public Set<Event> getActiveAndAfterToday() {
        return eventRepository.findByEventStatusAndAfterTodayEvent(EventStatus.WAITING);
    }
    @Override
    public Optional<Event> getById(Long id) {
        return eventRepository.findById(id);
    }
    @Override
    public Optional<Event> getByIdAndPresentationId(Long id, Long idPresentation) {
        return eventRepository.findByIdAndPresentation(id, idPresentation);
    }
}