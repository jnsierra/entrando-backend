package co.com.entrando.datos.repository;

import co.com.entrando.datos.entity.UserTicket;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserTicket, Long>, CrudRepository<UserTicket,Long> {
    Optional<UserTicket> findByEmail(String email);
    Optional<UserTicket> findByEmailAndPassword(String email, String password);
    @Modifying
    @Transactional
    Integer updateAttempts(@Param("id") Long id, @Param("attempts") Long attempts);
    @Modifying
    Integer updatePassword(@Param("email")String email, @Param("password")String password);
}