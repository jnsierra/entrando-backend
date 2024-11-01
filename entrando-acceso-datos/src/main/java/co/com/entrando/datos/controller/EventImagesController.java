package co.com.entrando.datos.controller;

import co.com.entrando.datos.entity.EventImages;
import co.com.entrando.datos.mapper.EventImagesMapper;
import co.com.entrando.datos.service.EventImagesService;
import co.com.entrando.dto.EventImagesDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("/v.1/event_images")
public class EventImagesController {
    private final EventImagesService eventImagesService;
    @Autowired
    public EventImagesController(EventImagesService eventImagesService) {
        this.eventImagesService = eventImagesService;
    }
    @PostMapping(value = "/", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<EventImagesDto> save(@RequestBody EventImagesDto eventImagesDto){
        EventImages entity = EventImagesMapper.INSTANCE.map(eventImagesDto);
        return new ResponseEntity<>(EventImagesMapper.INSTANCE.map( eventImagesService.save( entity ) ), HttpStatus.CREATED);
    }
    @GetMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Set<EventImagesDto>> getAll(){
        Set<EventImages> entities = eventImagesService.findAll();
        if(entities.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(EventImagesMapper.INSTANCE.map(entities));
    }
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<EventImagesDto> getById(@PathVariable(value = "id")Long id){
        Optional<EventImages> entity = eventImagesService.findById(id);
        if(!entity.isPresent()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(EventImagesMapper.INSTANCE.map(entity.get()));
    }

    @GetMapping(value = "/event/{idEvent}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Set<EventImagesDto>> getFindById(@PathVariable(value = "idEvent")Long idEvent){
        Set<EventImages> entities = eventImagesService.findByEvent(idEvent);
        if(entities.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(EventImagesMapper.INSTANCE.map(entities));
    }
}