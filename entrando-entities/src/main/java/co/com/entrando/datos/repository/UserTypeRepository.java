package co.com.entrando.datos.repository;

import co.com.entrando.datos.entity.UserType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserTypeRepository extends CrudRepository<UserType,Long>, JpaRepository<UserType, Long> {
    UserType findByType(String type);
}