package co.com.entrando.pub.controller;

import co.com.entrando.datos.entity.ConfigEvent;
import co.com.entrando.dto.ConfigEventDto;
import co.com.entrando.pub.mapper.ConfigEventMapper;
import co.com.entrando.pub.service.ConfigEventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/v.1/config_event")
public class ConfigEventController {

    private final ConfigEventService configEventService;
    @Autowired
    public ConfigEventController(ConfigEventService configEventService) {
        this.configEventService = configEventService;
    }
    @GetMapping(value = "/event/{idEvent}/presentation/{idPresentation}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ConfigEventDto> get(@PathVariable(value = "idEvent")Long idEvent, @PathVariable(value = "idPresentation")Long idPresentation){
        Optional<ConfigEvent> entity = configEventService.findByEventIdAndPresentationId(idEvent, idPresentation);
        if(!entity.isPresent()){
            return ResponseEntity.noContent()
                    .build();
        }
        return ResponseEntity.ok(ConfigEventMapper.INSTANCE.map(entity.get()));
    }
}