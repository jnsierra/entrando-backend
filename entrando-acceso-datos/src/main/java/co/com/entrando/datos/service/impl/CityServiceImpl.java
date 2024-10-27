package co.com.entrando.datos.service.impl;

import co.com.entrando.datos.entity.City;
import co.com.entrando.datos.entity.CityId;
import co.com.entrando.datos.repository.CityRepository;
import co.com.entrando.datos.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Service
public class CityServiceImpl implements CityService {
    private final CityRepository cityRepository;
    @Autowired
    public CityServiceImpl(CityRepository cityRepository) {
        this.cityRepository = cityRepository;
    }
    @Override
    public Optional<City> getById(Long cityCode, Long departmentCode) {
        return cityRepository.findById(new CityId(cityCode.intValue(),departmentCode.intValue()));
    }
    @Override
    public Set<City> getCitiesByDepartment(Long departmentCode) {
        return cityRepository.findByDepartment(departmentCode);
    }
}
