package co.com.entrando.datos.service.impl;

import co.com.entrando.datos.entity.MusicBand;
import co.com.entrando.datos.repository.MusicBandRepository;
import co.com.entrando.datos.service.MusicBandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class MusicBandServiceImpl implements MusicBandService {
    private final MusicBandRepository musicBandRepository;

    @Autowired
    public MusicBandServiceImpl(MusicBandRepository musicBandRepository) {
        this.musicBandRepository = musicBandRepository;
    }

    @Override
    public MusicBand save(MusicBand musicBand) {
        return musicBandRepository.save(musicBand);
    }

    @Override
    public Optional<MusicBand> getById(Long id) {
        return musicBandRepository.findById(id);
    }

    @Override
    public Set<MusicBand> getAll() {
        return new HashSet<>(musicBandRepository.findAll());
    }
}
