package co.com.entrando.datos.service.impl;

import co.com.entrando.datos.entity.Country;
import co.com.entrando.datos.repository.CountryRepository;
import co.com.entrando.datos.service.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class CountryServiceImpl implements CountryService {

    private final CountryRepository countryRepository;
    @Autowired
    public CountryServiceImpl(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    @Override
    public Set<Country> findAll() {
        return new HashSet<>(this.countryRepository.findAll());
    }
}
