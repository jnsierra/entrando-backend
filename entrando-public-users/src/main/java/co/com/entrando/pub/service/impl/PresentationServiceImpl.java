package co.com.entrando.pub.service.impl;

import co.com.entrando.datos.entity.Presentation;
import co.com.entrando.datos.repository.PresentationRepository;
import co.com.entrando.pub.service.PresentationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class PresentationServiceImpl implements PresentationService {
    private final PresentationRepository presentationRepository;
    @Autowired
    public PresentationServiceImpl(PresentationRepository presentationRepository) {
        this.presentationRepository = presentationRepository;
    }
    @Override
    public Set<Presentation> findByEvent(Long eventId) {
        return presentationRepository.findByEvent(eventId);
    }
}