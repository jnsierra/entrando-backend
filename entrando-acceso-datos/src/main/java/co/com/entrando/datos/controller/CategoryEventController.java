package co.com.entrando.datos.controller;

import co.com.entrando.datos.entity.CategoryEvent;
import co.com.entrando.datos.mapper.CategoryEventMapper;
import co.com.entrando.datos.service.CategoryEventService;
import co.com.entrando.dto.CategoryEventDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("/v.1/category_event")
public class CategoryEventController {
    private final CategoryEventService categoryEventService;
    @Autowired
    public CategoryEventController(CategoryEventService categoryEventService) {
        this.categoryEventService = categoryEventService;
    }
    @PostMapping(value = "/", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CategoryEventDto> save(@RequestBody CategoryEventDto categoryEventDto){
        CategoryEvent entity = CategoryEventMapper.INSTANCE.map(categoryEventDto);
        return ResponseEntity.ok(CategoryEventMapper.INSTANCE.map(categoryEventService.save(entity)));
    }
    @GetMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Set<CategoryEventDto>> get(){
        return new ResponseEntity<>(CategoryEventMapper.INSTANCE.map(categoryEventService.findAll()), HttpStatus.OK);
    }
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CategoryEventDto> getById(@PathVariable(value = "id")Long id){
        Optional<CategoryEvent> entity = categoryEventService.findById(id);
        if(!entity.isPresent()){
            return ResponseEntity.noContent().build();
        }
        return new ResponseEntity<>(CategoryEventMapper.INSTANCE.map(entity.get()), HttpStatus.OK);
    }
}
