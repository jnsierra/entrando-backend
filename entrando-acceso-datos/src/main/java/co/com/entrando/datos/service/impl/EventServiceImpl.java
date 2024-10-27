package co.com.entrando.datos.service.impl;

import co.com.entrando.datos.entity.Event;
import co.com.entrando.datos.repository.EventRepository;
import co.com.entrando.datos.service.EventService;
import co.com.entrando.enumeration.EventStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
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
    public Event save(Event event) {
        return eventRepository.save(event);
    }

    @Override
    public Optional<Event> findById(Long id) {
        return eventRepository.findById(id);
    }
    @Override
    public Set<Event> getAll(){
        return new HashSet<>(eventRepository.findAll());
    }
    @Transactional
    @Override
    public Boolean updateEventStatus(Long id, EventStatus eventStatus) {
        return eventRepository.updateEventStatus(id, eventStatus.toString()) == 1;
    }
}
