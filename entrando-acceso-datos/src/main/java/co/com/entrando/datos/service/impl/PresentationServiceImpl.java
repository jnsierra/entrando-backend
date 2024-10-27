package co.com.entrando.datos.service.impl;

import co.com.entrando.datos.entity.Presentation;
import co.com.entrando.datos.repository.PresentationRepository;
import co.com.entrando.datos.service.PresentationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class PresentationServiceImpl implements PresentationService {
    private final PresentationRepository presentationRepository;

    @Autowired
    public PresentationServiceImpl(PresentationRepository presentationRepository) {
        this.presentationRepository = presentationRepository;
    }

    @Override
    public Presentation save(Presentation presentation) {
        return presentationRepository.save(presentation);
    }

    @Override
    public Optional<Presentation> getById(Long id) {
        return presentationRepository.findById(id);
    }

    @Override
    public Set<Presentation> getAll() {
        return new HashSet<>(presentationRepository.findAll());
    }

    @Override
    public Set<Presentation> findByEvent(Long eventId) {
        return new HashSet<>(presentationRepository.findByEvent(eventId));
    }
}