package co.com.entrando.datos.service.impl;

import co.com.entrando.datos.entity.EventImage;
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
    public EventImage save(EventImage eventImage) {
        return eventImagesRepository.save(eventImage);
    }

    @Override
    public Set<EventImage> findAll() {
        return new HashSet<>(eventImagesRepository.findAll());
    }

    @Override
    public Optional<EventImage> findById(Long id) {
        return eventImagesRepository.findById(id);
    }
    @Override
    public Set<EventImage> findByEvent(Long idEvent) {
        return eventImagesRepository.findByEvent(idEvent);
    }
}
