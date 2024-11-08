package co.com.entrando.pub.service;


import co.com.entrando.datos.entity.CategoryEvent;

import java.util.Optional;
import java.util.Set;

public interface CategoryEventService {
    Optional<CategoryEvent> findById(Long id);
    Set<CategoryEvent> getAll();
}
