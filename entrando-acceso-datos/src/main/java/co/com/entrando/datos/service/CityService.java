package co.com.entrando.datos.service;

import co.com.entrando.datos.entity.City;

import java.util.Optional;
import java.util.Set;

public interface CityService {
    Optional<City> getById(Long cityCode,Long departmentCode);
    Set<City> getCitiesByDepartment(Long departmentCode);
}
