package co.com.entrando.datos.service.impl;

import co.com.entrando.datos.entity.EventImages;
import co.com.entrando.datos.repository.EventImagesRepository;
import co.com.entrando.datos.service.EventImagesService;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class EventImagesServiceImpl implements EventImagesService {
    private final EventImagesRepository eventImagesRepository;
    public EventImagesServiceImpl(EventImagesRepository eventImagesRepository) {
        this.eventImagesRepository = eventImagesRepository;
    }
    @Override
    public EventImages save(EventImages eventImage) {
        return eventImagesRepository.save(eventImage);
    }

    @Override
    public Set<EventImages> findAll() {
        return new HashSet<>(eventImagesRepository.findAll());
    }

    @Override
    public Optional<EventImages> findById(Long id) {
        return eventImagesRepository.findById(id);
    }
    @Override
    public Set<EventImages> findByEvent(Long idEvent) {
        return eventImagesRepository.findByEvent(idEvent);
    }
}
