package co.com.entrando.datos.service;

import co.com.entrando.datos.entity.MusicBand;

import java.util.Optional;
import java.util.Set;

public interface MusicBandService {
    MusicBand save(MusicBand musicBand);
    Optional<MusicBand> getById(Long id);
    Set<MusicBand> getAll();
}
