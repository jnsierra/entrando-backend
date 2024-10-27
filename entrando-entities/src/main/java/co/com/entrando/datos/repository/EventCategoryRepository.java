package co.com.entrando.datos.repository;

import co.com.entrando.datos.entity.CategoryEvent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface EventCategoryRepository extends JpaRepository<CategoryEvent, Integer>, CrudRepository<CategoryEvent, Integer> {
}
