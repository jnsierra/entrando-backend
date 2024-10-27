package co.com.entrando.datos.controller;

import co.com.entrando.datos.entity.Event;
import co.com.entrando.datos.mapper.EventMapper;
import co.com.entrando.datos.service.EventService;
import co.com.entrando.dto.EventDto;
import co.com.entrando.enumeration.EventStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("/v.1/event")
public class EventController {
    private final EventService eventService;

    @Autowired
    public EventController(EventService eventService ) {
        this.eventService = eventService;
    }
    @PostMapping(value = "/", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @Secured({"ROLE_ADMIN", "ROLE_MANAGER"})
    public ResponseEntity<EventDto> save(@RequestBody EventDto eventDto){
        Event entity = EventMapper.INSTANCE.map(eventDto);
        return new ResponseEntity<>(EventMapper.INSTANCE.map( eventService.save( entity ) ), HttpStatus.CREATED);
    }
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @Secured({"ROLE_ADMIN", "ROLE_MANAGER"})
    public ResponseEntity<EventDto> get(@PathVariable(value = "id")Long id){
        Optional<Event> entity = eventService.findById(id);
        if(!entity.isPresent()){
            return ResponseEntity.noContent()
                    .build();
        }
        return ResponseEntity.ok(EventMapper.INSTANCE.map(entity.get()));
    }
    @GetMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    @Secured({"ROLE_ADMIN", "ROLE_MANAGER"})
    public ResponseEntity<Set<EventDto>> getAll(){
        Set<Event> entities = eventService.getAll();
        if(entities.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(EventMapper.INSTANCE.map(entities));
    }
    @PutMapping(value = "/{eventId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Boolean updateStatusEvent(@PathVariable("eventId") Long eventId
            , @RequestParam EventStatus eventStatus){
        return eventService.updateEventStatus(eventId, eventStatus);
    }
}