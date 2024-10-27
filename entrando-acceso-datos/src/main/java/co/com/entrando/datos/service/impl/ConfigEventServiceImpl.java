package co.com.entrando.datos.service.impl;

import co.com.entrando.datos.entity.ConfigEvent;
import co.com.entrando.datos.repository.ConfigEventRepository;
import co.com.entrando.datos.service.ConfigEventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class ConfigEventServiceImpl implements ConfigEventService {
    private ConfigEventRepository configEventRepository;
    @Autowired
    public ConfigEventServiceImpl(ConfigEventRepository configEventRepository) {
        this.configEventRepository = configEventRepository;
    }
    @Override
    public ConfigEvent save(ConfigEvent entity) {
        return configEventRepository.save(entity);
    }
    @Override
    public Optional<ConfigEvent> findById(Long id) {
        return configEventRepository.findById(id);
    }
    @Override
    public Set<ConfigEvent> getAll() {
        return new HashSet<>(configEventRepository.findAll());
    }
    @Override
    public Set<ConfigEvent> findByEventId(Long eventId) {
        return configEventRepository.findByEventId(eventId);
    }

    @Override
    public Optional<Long> recordSale(Long eventId, Long presentationId) {
        Optional<ConfigEvent> entity = configEventRepository.getByEventIdAndPresentation(eventId, presentationId);
        if(!entity.isPresent()){
            return Optional.empty();
        }
        entity.get().setNumberOfTicketsSold(entity.get().getNumberOfTicketsSold().add(BigDecimal.ONE));
        return Optional.of(entity.get().getId().longValue());
    }
    @Override
    public Optional<ConfigEvent> findByEventIdAndPresentationId(Long eventId, Long presentationId) {
        return configEventRepository.getByEventIdAndPresentation(presentationId,eventId);
    }
}