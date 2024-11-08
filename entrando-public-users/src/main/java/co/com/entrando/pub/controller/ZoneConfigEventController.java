package co.com.entrando.pub.controller;

import co.com.entrando.datos.entity.ZoneConfigEvent;
import co.com.entrando.dto.ZoneConfigEventViewDto;
import co.com.entrando.pub.mapper.ZoneConfigEventMapper;
import co.com.entrando.pub.service.ZoneConfigEventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/v.1/zone_config_event")
public class ZoneConfigEventController {
    private final ZoneConfigEventService zoneConfigEventService;
    @Autowired
    public ZoneConfigEventController(ZoneConfigEventService zoneConfigEventService) {
        this.zoneConfigEventService = zoneConfigEventService;
    }
    @Transactional
    @GetMapping(value = "/event/{idEvent}/presentation/{idPresentation}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Set<ZoneConfigEventViewDto>> getByEventPresentation(@PathVariable(value = "idEvent")Long idEvent, @PathVariable(value = "idPresentation")Long idPresentation){
        Set<ZoneConfigEvent> entities = zoneConfigEventService.getByIdEventAndPresentId(idEvent, idPresentation);
        if(entities.isEmpty()){
            return ResponseEntity.noContent()
                    .build();
        }
        return ResponseEntity.ok(entities.stream().map(ZoneConfigEventMapper.INSTANCE::mapToView).collect(Collectors.toSet()));
    }
}
