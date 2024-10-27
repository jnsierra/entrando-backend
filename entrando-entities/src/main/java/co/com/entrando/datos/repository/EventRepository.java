package co.com.entrando.datos.repository;

import co.com.entrando.datos.entity.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;

@Repository
public interface EventRepository extends JpaRepository<Event, Long>, CrudRepository<Event, Long> {
    Set<Event> findByEventStatus(String eventStatus);
    Optional<Event> findByIdAndPresentation(Long eventId, Long presentationId);
    Set<Event> findByEventStatusAndAfterTodayEvent(String eventStatus);
    @Modifying
    Integer updateEventStatus(Long id, String eventStatus);
}
