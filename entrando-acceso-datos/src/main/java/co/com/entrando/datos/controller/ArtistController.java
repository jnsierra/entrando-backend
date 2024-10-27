package co.com.entrando.datos.controller;

import co.com.entrando.datos.entity.Artist;
import co.com.entrando.datos.mapper.ArtistMapper;
import co.com.entrando.datos.service.ArtistService;
import co.com.entrando.dto.ArtistDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("/v.1/artist")
public class ArtistController {
    private final ArtistService artistService;
    @Autowired
    public ArtistController(ArtistService artistService) {
        this.artistService = artistService;
    }
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ArtistDto> get(@PathVariable(value = "id") Long id){
        Optional<Artist> response = artistService.getById(id);
        if(!response.isPresent()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(ArtistMapper.INSTANCE.map(response.get()));
    }
    @GetMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Set<ArtistDto>> get(){
        Set<Artist> response = artistService.getAll();
        if(response.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(ArtistMapper.INSTANCE.map(response));
    }
    @PostMapping(value = "/", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ArtistDto> save(@RequestBody ArtistDto artistDto){
        Artist entity = ArtistMapper.INSTANCE.map(artistDto);
        return new ResponseEntity<>(ArtistMapper.INSTANCE.map( artistService.save( entity ) ), HttpStatus.CREATED);
    }
}
