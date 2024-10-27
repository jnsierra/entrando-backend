package co.com.entrando.datos.repository;

import co.com.entrando.datos.entity.EventCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface EventCategoryRepository extends JpaRepository<EventCategory, Integer>, CrudRepository<EventCategory, Integer> {
}
