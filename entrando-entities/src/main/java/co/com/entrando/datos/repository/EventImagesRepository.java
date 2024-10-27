package co.com.entrando.datos.repository;

import co.com.entrando.datos.entity.EventImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface EventImagesRepository extends JpaRepository<EventImage, Long >, CrudRepository<EventImage, Long> {
    Set<EventImage> findByEventAndTypeImages(Long idEvent, String typeImages);
    Set<EventImage> findByEvent(Long idEvent);
}