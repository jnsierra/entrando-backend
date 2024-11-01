package co.com.entrando.datos.controller;


import co.com.entrando.datos.entity.Zone;
import co.com.entrando.datos.mapper.ZoneMapper;
import co.com.entrando.datos.service.ZoneService;
import co.com.entrando.dto.ZoneDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("/v.1/zone")
public class ZoneController {
    private final ZoneService zoneService;
    @Autowired
    public ZoneController(ZoneService zoneService) {
        this.zoneService = zoneService;
    }
    @PostMapping(value = "/", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE )
    public ResponseEntity<ZoneDto> save(@RequestBody ZoneDto zoneDto){
        Zone entity = ZoneMapper.INSTANCE.map(zoneDto);
        return ResponseEntity.ok(ZoneMapper.INSTANCE.map( zoneService.save(entity) ));
    }
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ZoneDto> getById(@PathVariable(value = "id")Long id){
        Optional<Zone> entity = zoneService.getById(id);
        if(!entity.isPresent()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(ZoneMapper.INSTANCE.map(entity.get()));
    }
    @GetMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Set<ZoneDto>> getAll(){
        Set<Zone> entities = zoneService.getAll();
        if(entities.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(ZoneMapper.INSTANCE.map(entities));
    }
}