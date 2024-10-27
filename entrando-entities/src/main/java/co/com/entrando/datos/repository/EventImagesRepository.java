package co.com.entrando.datos.repository;

import co.com.entrando.datos.entity.EventImages;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface EventImagesRepository extends JpaRepository<EventImages, Long >, CrudRepository<EventImages, Long> {
    Set<EventImages> findByEventAndTypeImages(Long idEvent, String typeImages);
    Set<EventImages> findByEvent(Long idEvent);
}