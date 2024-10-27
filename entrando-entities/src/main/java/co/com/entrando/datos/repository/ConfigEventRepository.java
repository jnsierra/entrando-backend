package co.com.entrando.datos.repository;

import co.com.entrando.datos.entity.ConfigEvent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;

@Repository
public interface ConfigEventRepository extends JpaRepository<ConfigEvent, Long>, CrudRepository<ConfigEvent, Long> {
    Optional<ConfigEvent> getByEventIdAndPresentation(Long idPresentation, Long idEvent);
    Set<ConfigEvent> findByEventId(Long idEvent);
}
