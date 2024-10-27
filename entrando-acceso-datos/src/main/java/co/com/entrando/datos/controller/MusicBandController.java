package co.com.entrando.datos.controller;

import co.com.entrando.datos.entity.MusicBand;
import co.com.entrando.datos.mapper.MusicBandMapper;
import co.com.entrando.datos.service.impl.MusicBandServiceImpl;
import co.com.entrando.dto.MusicBandDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("/v.1/musicBand")
public class MusicBandController {
    private MusicBandServiceImpl musicBandService;
    @Autowired
    public MusicBandController(MusicBandServiceImpl musicBandService) {
        this.musicBandService = musicBandService;
    }
    @GetMapping( value = "/{id}")
    public ResponseEntity<MusicBandDto> get(@PathVariable("id")Long id){
        Optional<MusicBand> entity = musicBandService.getById(id);
        if(!entity.isPresent()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(MusicBandMapper.INSTANCE.map(entity.get()));
    }
    @GetMapping( value = "/")
    public ResponseEntity<Set<MusicBandDto>> getAll(){
        Set<MusicBand> entities = musicBandService.getAll();
        if(entities.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(MusicBandMapper.INSTANCE.map(entities));
    }
    @PostMapping(value = "/", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<MusicBandDto> save(@RequestBody MusicBandDto musicBandDto){
        MusicBand entity = MusicBandMapper.INSTANCE.map(musicBandDto);
        return ResponseEntity.ok(MusicBandMapper.INSTANCE.map(musicBandService.save(entity)));
    }
}
