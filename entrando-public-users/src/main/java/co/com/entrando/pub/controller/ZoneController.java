package co.com.entrando.pub.controller;


import co.com.entrando.datos.entity.Zone;
import co.com.entrando.dto.ZoneDto;
import co.com.entrando.pub.mapper.ZoneMapper;
import co.com.entrando.pub.service.ZoneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
@RequestMapping("/v.1/zone")
public class ZoneController {
    private final ZoneService zoneService;
    @Autowired
    public ZoneController(ZoneService zoneService) {
        this.zoneService = zoneService;
    }
    @GetMapping(value = "/category/{idCategory}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Set<ZoneDto>> getByCategory(@PathVariable(value = "idCategory")Long idCategory){
        Set<Zone> entities = zoneService.getByCategory(idCategory);
        if(entities.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(ZoneMapper.INSTANCE.map(entities));
    }
}
