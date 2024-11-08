package co.com.entrando.pub.controller;

import co.com.entrando.datos.entity.CategoryEvent;
import co.com.entrando.dto.CategoryEventDto;
import co.com.entrando.pub.mapper.CategoryEventMapper;
import co.com.entrando.pub.service.CategoryEventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CategoryEventDto> findById(@PathVariable(value = "id")Long id){
        Optional<CategoryEvent> entity = categoryEventService.findById(id);
        if(!entity.isPresent()){
            return ResponseEntity.noContent()
                    .build();
        }
        return ResponseEntity.ok(CategoryEventMapper.INSTANCE.map(entity.get()));
    }
    @GetMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Set<CategoryEventDto>> getAll(){
        Set<CategoryEvent> response = categoryEventService.getAll();
        if(response.isEmpty()){
            return ResponseEntity.noContent()
                    .build();
        }
        return ResponseEntity.ok(CategoryEventMapper.INSTANCE.map(response));
    }
}
