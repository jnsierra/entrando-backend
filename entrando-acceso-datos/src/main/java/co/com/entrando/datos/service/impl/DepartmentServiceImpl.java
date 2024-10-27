package co.com.entrando.datos.service.impl;

import co.com.entrando.datos.entity.Department;
import co.com.entrando.datos.repository.DepartmentRepository;
import co.com.entrando.datos.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Service
public class DepartmentServiceImpl implements DepartmentService {
    private final DepartmentRepository departmentRepository;
    @Autowired
    public DepartmentServiceImpl(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }
    @Override
    public Set<Department> getSetDepartmentByCountry(Long id) {
        return departmentRepository.findByCountry(id);
    }
    @Override
    public Optional<Department> getById(Long id) {
        return departmentRepository.findById(id);
    }
}