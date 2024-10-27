package co.com.entrando.datos.service.impl;


import co.com.entrando.datos.entity.Artist;
import co.com.entrando.datos.repository.ArtistRepository;
import co.com.entrando.datos.service.ArtistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class ArtistServiceImpl implements ArtistService {
    private final ArtistRepository artistRepository;

    @Autowired
    public ArtistServiceImpl(ArtistRepository artistRepository) {
        this.artistRepository = artistRepository;
    }

    @Override
    public Artist save(Artist entity) {
        return artistRepository.save(entity);
    }

    @Override
    public Optional<Artist> getById(Long id) {
        return artistRepository.findById(id.intValue());
    }

    @Override
    public Set<Artist> getAll() {
        return new HashSet<>(artistRepository.findAll());
    }
}