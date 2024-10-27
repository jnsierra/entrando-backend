package co.com.entrando.datos.service.impl;

import co.com.entrando.datos.entity.CategoryEvent;
import co.com.entrando.datos.service.CategoryEventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import co.com.entrando.datos.repository.EventCategoryRepository;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class CategoryEventServiceImpl implements CategoryEventService {
    private final EventCategoryRepository categoryEventRepository;

    @Autowired
    public CategoryEventServiceImpl(EventCategoryRepository categoryEventRepository) {
        this.categoryEventRepository = categoryEventRepository;
    }

    @Override
    public CategoryEvent save(CategoryEvent event) {
        return categoryEventRepository.save(event);
    }

    @Override
    public Set<CategoryEvent> findAll() {
        return new HashSet<>(categoryEventRepository.findAll());
    }

    @Override
    public Optional<CategoryEvent> findById(Long id) {
        return categoryEventRepository.findById(id.intValue());
    }
}
