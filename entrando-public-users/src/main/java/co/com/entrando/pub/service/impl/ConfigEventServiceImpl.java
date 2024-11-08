package co.com.entrando.pub.service.impl;

import co.com.entrando.datos.entity.ConfigEvent;
import co.com.entrando.datos.repository.ConfigEventRepository;
import co.com.entrando.pub.service.ConfigEventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ConfigEventServiceImpl implements ConfigEventService {
    private final ConfigEventRepository configEventRepository;
    @Autowired
    public ConfigEventServiceImpl(ConfigEventRepository configEventRepository) {
        this.configEventRepository = configEventRepository;
    }
    @Override
    public Optional<ConfigEvent> findByEventIdAndPresentationId(Long idEvent, Long idPresentation) {
        return configEventRepository.getByEventIdAndPresentation(idPresentation, idEvent);
    }
}
