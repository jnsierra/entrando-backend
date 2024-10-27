package co.com.entrando.datos.controller;

import co.com.entrando.datos.entity.Category;
import co.com.entrando.datos.mapper.CategoryMapper;
import co.com.entrando.datos.service.CategoryService;
import co.com.entrando.dto.CategoryDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("/v.1/category")
public class CategoryController {
    private CategoryService categoryService;
    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }
    @PostMapping(value = "/", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CategoryDto> save(@RequestBody CategoryDto categoryDto){
        Category entity = CategoryMapper.INSTANCE.map(categoryDto);
        return new ResponseEntity<>(CategoryMapper.INSTANCE.map( categoryService.save( entity ) ), HttpStatus.CREATED);
    }
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CategoryDto> getById(@PathVariable(value = "id")Long id){
        Optional<Category> entity = categoryService.findById(id);
        if(!entity.isPresent()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(CategoryMapper.INSTANCE.map( entity.get() ));
    }
    @GetMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Set<CategoryDto>> getById(){
        Set<Category> entities = categoryService.findAll();
        if(entities.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(CategoryMapper.INSTANCE.map( entities ));
    }
}
