package co.com.entrando.datos.repository;

import co.com.entrando.datos.entity.Presentation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface PresentationRepository extends JpaRepository<Presentation, Long>, CrudRepository<Presentation, Long> {
    Set<Presentation> findByEvent(Long idEvent);
}
