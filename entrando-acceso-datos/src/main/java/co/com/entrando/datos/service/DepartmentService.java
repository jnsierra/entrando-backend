package co.com.entrando.datos.service;

import co.com.entrando.datos.entity.Department;

import java.util.Optional;
import java.util.Set;

public interface DepartmentService {
    Set<Department> getSetDepartmentByCountry(Long id);

    Optional<Department> getById(Long id);
}
