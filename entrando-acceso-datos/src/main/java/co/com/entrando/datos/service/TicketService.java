package co.com.entrando.datos.service;


import co.com.entrando.datos.entity.Ticket;
import co.com.entrando.datos.entity.TicketPk;
import co.com.entrando.datos.filtering.SearchRequest;
import co.com.entrando.dto.TicketViewDto;
import co.com.entrando.dto.responses.GenericQuery;
import co.com.entrando.enumeration.StatusTicket;

import java.util.Optional;
import java.util.Set;

public interface TicketService {
    Ticket save(Ticket ticket);
    Optional<Ticket> getById(TicketPk id);
    Set<TicketViewDto> getByEventIdAndPresentationId(Long eventId, Long presentationId, Integer records, Integer page);
    Integer countByEventIdAndPresentationId(Long eventId, Long presentationId);
    Integer updateState( StatusTicket state,
                         Long eventId,
                         Long zoneId,
                         Long categoryId,
                         Long presentationId,
                         Long numberTicket,
                         String confirmationNumber,
                         String user);
    Optional<Ticket> buyTicket( StatusTicket state,
                         Long eventId,
                         Long zoneId,
                         Long categoryId,
                         Long presentationId,
                         Long numberTicket);
    Set<Ticket> getByEmailAndEventAndPresentation(String email, Long eventId, Long presentationId);
    GenericQuery<TicketViewDto> searchTickets(SearchRequest request);
}