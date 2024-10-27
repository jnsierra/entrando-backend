package co.com.entrando.datos.repository;

import co.com.entrando.datos.entity.Ticket;
import co.com.entrando.datos.entity.TicketPk;
import co.com.entrando.enumeration.StatusTicket;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, TicketPk>, CrudRepository<Ticket, TicketPk>, JpaSpecificationExecutor<Ticket> {

    List<Ticket> getByEventIdAndPresentationId(Long eventId, Long presentationId, Pageable pageable);

    Integer countByEventIdAndPresentationId(Long eventId, Long presentationId);
    @Modifying
    Integer updateState(@Param(value = "state") StatusTicket state,
                        @Param(value = "eventId") Long eventId,
                        @Param(value = "zoneId") Long zoneId,
                        @Param(value = "categoryId") Long categoryId,
                        @Param(value = "presentationId") Long presentationId,
                        @Param(value = "numberTicket")Long numberTicket,
                        @Param(value = "confirmationNumber")String confirmationNumber,
                        @Param(value = "user")String user);
    Set<Ticket> getByEventAndPresentationAndZoneAndCategory(Long eventId, Long presentationId, Long zoneId, Long categoryId);
    Set<Ticket> getByEmailAndEventAndPresentation(String email, Long eventId, Long presentationId);
}
