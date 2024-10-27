package co.com.entrando.datos.service;

import co.com.entrando.datos.entity.Category;

import java.util.Optional;
import java.util.Set;

public interface CategoryService {
    Category save(Category category);
    Set<Category> findAll();
    Optional<Category> findById(Long id);
}
