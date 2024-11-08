package co.com.entrando.pub.service.impl;

import co.com.entrando.datos.entity.Ticket;
import co.com.entrando.datos.repository.TicketRepository;
import co.com.entrando.pub.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;
@Service
public class TicketServiceImpl implements TicketService {
    private final TicketRepository ticketRepository;
    @Autowired
    public TicketServiceImpl(TicketRepository ticketRepository) {
        this.ticketRepository = ticketRepository;
    }
    @Override
    public Set<Ticket> getByEventAndPresentationAndZoneAndCategory(Long eventId, Long presentationId, Long zoneId, Long categoryId) {
        return ticketRepository.getByEventAndPresentationAndZoneAndCategory(eventId,presentationId,zoneId,categoryId);
    }
}
