package co.com.entrando.pub.service.impl;

import co.com.entrando.datos.entity.Category;
import co.com.entrando.datos.repository.CategoryRepository;
import co.com.entrando.pub.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;
    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }
    @Override
    public Set<Category> getAll() {
        return new HashSet<>(categoryRepository.findAll());
    }
    @Override
    public Set<Category> getByEventAndPresentation(Long idEvent, Long idPresentation) {
        return categoryRepository.getByEventAndPresentation(idEvent, idPresentation);
    }
}
