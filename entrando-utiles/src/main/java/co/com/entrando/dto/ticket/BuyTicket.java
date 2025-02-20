package co.com.entrando.dto.ticket;

import lombok.Data;

import java.util.Set;

@Data
public class BuyTicket {
    private Long eventId;
    private Long presentationId;
    private Long zoneId;
    private Long categoryId;
    private Long numberOfTickets;
    private Set<Long> numberTickets;
}