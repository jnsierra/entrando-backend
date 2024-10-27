package co.com.entrando.datos.repository;

import co.com.entrando.datos.entity.Artist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArtistRepository extends JpaRepository<Artist, Integer>, CrudRepository<Artist, Integer> {
}
