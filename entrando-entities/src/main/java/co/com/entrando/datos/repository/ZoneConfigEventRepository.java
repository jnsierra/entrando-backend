package co.com.entrando.datos.repository;

import co.com.entrando.datos.entity.ZoneConfigEvent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;

@Repository
public interface ZoneConfigEventRepository extends JpaRepository<ZoneConfigEvent, Long>, CrudRepository<ZoneConfigEvent, Long> {
    Set<ZoneConfigEvent> getZoneConfigByEvent(Long idEvent);
    Set<ZoneConfigEvent> getZoneConfigByEventAndPresentation(Long idEvent, Long idPresentation);
    Set<ZoneConfigEvent> getByConfigEventId(Long configEventId);
    Optional<ZoneConfigEvent> getByZoneAndConfigEvent(Long zoneId, Long configEventId);
    @Modifying
    Integer updateCreateTickets(Long id, boolean createTickets);
}