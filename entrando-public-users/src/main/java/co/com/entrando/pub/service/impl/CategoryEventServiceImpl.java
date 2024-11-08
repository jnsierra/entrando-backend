package co.com.entrando.pub.service.impl;

import co.com.entrando.datos.entity.CategoryEvent;
import co.com.entrando.datos.repository.EventCategoryRepository;
import co.com.entrando.pub.service.CategoryEventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public Optional<CategoryEvent> findById(Long id) {
        return categoryEventRepository.findById(id.intValue());
    }
    @Override
    public Set<CategoryEvent> getAll() {
        return new HashSet<>(categoryEventRepository.findAll());
    }
}