package co.com.entrando.datos.repository;

import co.com.entrando.datos.entity.City;
import co.com.entrando.datos.entity.CityPk;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface CityRepository extends JpaRepository<City, CityPk>, CrudRepository<City, CityPk> {

    Set<City> findByDepartment(Long departmentCode);
}
