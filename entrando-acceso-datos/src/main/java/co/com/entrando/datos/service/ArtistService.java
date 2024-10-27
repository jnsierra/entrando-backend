package co.com.entrando.datos.service;


import co.com.entrando.datos.entity.Artist;

import java.util.Optional;
import java.util.Set;

public interface ArtistService {
    Artist save(Artist entity);
    Optional<Artist> getById(Long id);
    Set<Artist> getAll();
}
