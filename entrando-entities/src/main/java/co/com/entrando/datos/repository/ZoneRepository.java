package co.com.entrando.datos.repository;

import co.com.entrando.datos.entity.Zone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface ZoneRepository extends JpaRepository<Zone, Long>, CrudRepository<Zone, Long> {
    Set<Zone> findByCategory(Long categoryId);
}
