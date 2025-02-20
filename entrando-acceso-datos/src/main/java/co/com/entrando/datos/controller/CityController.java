package co.com.entrando.datos.controller;

import co.com.entrando.datos.entity.City;
import co.com.entrando.datos.mapper.CityMapper;
import co.com.entrando.datos.service.CityService;
import co.com.entrando.dto.CityDto;
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
@RequestMapping("/v.1/city")
public class CityController {
    private final CityService cityService;
    @Autowired
    public CityController(CityService cityService) {
        this.cityService = cityService;
    }
    @GetMapping(value = "/{code}/{departmentCode}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CityDto> get(@PathVariable(value = "code") Long code, @PathVariable(value = "departmentCode") Long departmentCode){
        Optional<City> response = cityService.getById(code, departmentCode);
        if(!response.isPresent()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(CityMapper.INSTANCE.map(response.get()));
    }
    @GetMapping(value = "/department/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Set<CityDto>> getByDepartment(@PathVariable(value = "id") Long departmentCode){
        Set<City> response = cityService.getCitiesByDepartment(departmentCode);
        if(response.isEmpty()){
            return ResponseEntity
                    .noContent().build();
        }
        return ResponseEntity.ok(CityMapper.INSTANCE.map(response));
    }
}