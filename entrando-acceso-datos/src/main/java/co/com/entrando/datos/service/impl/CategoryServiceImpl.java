package co.com.entrando.datos.service.impl;

import co.com.entrando.datos.entity.Category;
import co.com.entrando.datos.repository.CategoryRepository;
import co.com.entrando.datos.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class CategoryServiceImpl implements CategoryService {
    private CategoryRepository categoryRepository;

    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Category save(Category category) {
        return categoryRepository.save(category);
    }

    @Override
    public Set<Category> findAll() {
        return new HashSet<>(categoryRepository.findAll());
    }

    @Override
    public Optional<Category> findById(Long id) {
        return categoryRepository.findById(id);
    }
}