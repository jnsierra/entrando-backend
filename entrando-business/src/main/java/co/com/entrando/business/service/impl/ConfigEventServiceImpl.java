package co.com.entrando.business.service.impl;

import co.com.entrando.business.client.ConfigEventClient;
import co.com.entrando.business.service.ConfigEventService;
import co.com.entrando.dto.ConfigEventDto;
import co.com.entrando.exception.BusinessException;
import co.com.entrando.exception.enumeration.TYPE_EXCEPTION;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.function.BiFunction;

@Service
public class ConfigEventServiceImpl implements ConfigEventService {
    private ConfigEventClient configEventClient;
    private final BiFunction<Long, Long, Optional<ConfigEventDto>> getConfigEventByEventAndPresentation =
            (event, presentation) -> {
                ResponseEntity<ConfigEventDto> response = configEventClient.getByEventAndPresentation(event, presentation);
                return HttpStatus.OK.equals(response.getStatusCode()) ? Optional.of(response.getBody()) : Optional.empty();
            };

    @Autowired
    public ConfigEventServiceImpl(ConfigEventClient configEventClient) {
        this.configEventClient = configEventClient;
    }

    @Override
    public Long getNumberOfTicketsEventAndPresentation(Long idEvent, Long idPresentation) {
        return getConfigEventByEventAndPresentation.apply(idEvent, idPresentation)
                .map(configEventDto -> configEventDto.getNumberOfTickets().longValue())
                .orElseThrow(() -> new BusinessException(1L, TYPE_EXCEPTION.ERROR, String.format("No existe configuracion del evento para el event Id %s y Presentation id %s", idEvent, idPresentation)));
    }

    public boolean existsConfigEvent(Long idEvent, Long idPresentation) {
        return getConfigEventByEventAndPresentation.apply(idEvent, idPresentation).isPresent();
    }
}
