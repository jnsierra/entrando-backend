package co.com.entrando.datos.repository;

import co.com.entrando.datos.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long>, CrudRepository<Department, Long> {

    Set<Department> findByCountry(@Param("idCountry") Long idCountry);
}
