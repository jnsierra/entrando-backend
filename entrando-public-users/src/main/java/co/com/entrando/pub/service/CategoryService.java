package co.com.entrando.pub.service;

import co.com.entrando.datos.entity.Category;

import java.util.Set;

public interface CategoryService {
    Set<Category> getAll();
    Set<Category> getByEventAndPresentation(Long idEvent, Long idPresentation);
}
