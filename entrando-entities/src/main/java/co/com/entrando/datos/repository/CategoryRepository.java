package co.com.entrando.datos.repository;

import co.com.entrando.datos.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface CategoryRepository extends JpaRepository<Category,Long>, CrudRepository<Category, Long> {
    Set<Category> getByEventAndPresentation(Long eventId, Long presentationId);
}
