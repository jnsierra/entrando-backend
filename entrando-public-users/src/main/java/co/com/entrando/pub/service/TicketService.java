package co.com.entrando.pub.service;


import co.com.entrando.datos.entity.Ticket;

import java.util.Set;

public interface TicketService {
    Set<Ticket> getByEventAndPresentationAndZoneAndCategory(Long eventId, Long presentationId, Long zoneId, Long categoryId);
}
