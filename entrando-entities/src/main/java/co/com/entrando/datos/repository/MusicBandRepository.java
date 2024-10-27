package co.com.entrando.datos.repository;

import co.com.entrando.datos.entity.MusicBand;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MusicBandRepository extends JpaRepository<MusicBand, Long>, CrudRepository<MusicBand, Long> {
}