package co.com.entrando.datos.service;

import co.com.entrando.datos.entity.CategoryEvent;

import java.util.Optional;
import java.util.Set;

public interface CategoryEventService {

    CategoryEvent save(CategoryEvent event);
    Set<CategoryEvent> findAll();
    Optional<CategoryEvent> findById(Long id);
}
