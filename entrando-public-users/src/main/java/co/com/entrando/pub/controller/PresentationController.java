package co.com.entrando.pub.controller;


import co.com.entrando.datos.entity.Presentation;
import co.com.entrando.dto.PresentationDto;
import co.com.entrando.pub.mapper.PresentationMapper;
import co.com.entrando.pub.service.PresentationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
@RequestMapping("/v.1/presentation")
public class PresentationController {
    private final PresentationService presentationService;
    @Autowired
    public PresentationController(PresentationService presentationService) {
        this.presentationService = presentationService;
    }
    @GetMapping(value = "/event/{idEvent}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Set<PresentationDto>> getByEvent(@PathVariable(value = "idEvent")Long idEvent){
        Set<Presentation> presentations = presentationService.findByEvent(idEvent);
        if(presentations.isEmpty()){
            return ResponseEntity.noContent()
                    .build();
        }
        return ResponseEntity.ok(PresentationMapper.INSTANCE.map(presentations));
    }
}
