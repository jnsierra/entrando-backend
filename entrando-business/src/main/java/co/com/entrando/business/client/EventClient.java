package co.com.entrando.business.client;

import co.com.entrando.dto.EventDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import co.com.entrando.enumeration.EventStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "eventoClient", url = "${endpoint.ms-data-access.protocol}://${endpoint.ms-data-access.host}:${endpoint.ms-data-access.port}/api-acceso-datos/v.1/event")
public interface EventClient {
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    EventDto get(@PathVariable(value = "id")Long id);
    @PutMapping(value = "/{eventId}", produces = MediaType.APPLICATION_JSON_VALUE)
    Boolean updateStatusEvent(@PathVariable("eventId") Long eventId
            , @RequestParam("eventStatus") EventStatus eventStatus);
}