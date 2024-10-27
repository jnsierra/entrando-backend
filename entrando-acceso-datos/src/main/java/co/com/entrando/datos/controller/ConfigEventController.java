package co.com.entrando.datos.controller;

import co.com.entrando.datos.entity.ConfigEvent;
import co.com.entrando.datos.mapper.ConfigEventMapper;
import co.com.entrando.datos.service.ConfigEventService;
import co.com.entrando.dto.ConfigEventDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("/v.1/config_event")
public class ConfigEventController {
    private final ConfigEventService configEventService;

    @Autowired
    public ConfigEventController(ConfigEventService configEventService) {
        this.configEventService = configEventService;
    }

    @PostMapping(value = "/", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ConfigEventDto> save(@RequestBody ConfigEventDto configEventDto) {
        ConfigEvent entity = ConfigEventMapper.INSTANCE.map(configEventDto);
        return ResponseEntity.ok(ConfigEventMapper.INSTANCE.map(configEventService.save(entity)));
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ConfigEventDto> get(@PathVariable(value = "id") Long id) {
        Optional<ConfigEvent> entity = configEventService.findById(id);
        if (!entity.isPresent()) {
            return ResponseEntity.noContent()
                    .build();
        }
        return ResponseEntity.ok(ConfigEventMapper.INSTANCE.map(entity.get()));
    }

    @GetMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Set<ConfigEventDto>> getAll() {
        Set<ConfigEvent> entities = configEventService.getAll();
        if (entities.isEmpty()) {
            return ResponseEntity.noContent()
                    .build();
        }
        return ResponseEntity.ok(ConfigEventMapper.INSTANCE.map(entities));
    }

    @GetMapping(value = "/event/{idEvent}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Set<ConfigEventDto>> getByEvent(@PathVariable(value = "idEvent") Long idEvent) {
        Set<ConfigEvent> entities = configEventService.findByEventId(idEvent);
        if (entities.isEmpty()) {
            return ResponseEntity.noContent()
                    .build();
        }
        return ResponseEntity.ok(ConfigEventMapper.INSTANCE.map(entities));
    }

    @GetMapping(value = "/event/{idEvent}/presentation/{idPresentation}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ConfigEventDto> getByEvent(@PathVariable(value = "idEvent") Long idEvent,
                                                     @PathVariable(value = "idPresentation") Long idPresentation) {
        Optional<ConfigEvent> entity = configEventService.findByEventIdAndPresentationId(idEvent, idPresentation);
        if (entity.isEmpty()) {
            return ResponseEntity.noContent()
                    .build();
        }
        return ResponseEntity.ok(ConfigEventMapper.INSTANCE.map(entity.get()));
    }
}