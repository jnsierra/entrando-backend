package co.com.entrando.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ZoneConfigEventDto {
    private Long id;
    private Long zoneId;
    private Long configEventId;
    private Long numberOfTickets;
    private BigDecimal cost;
    private Long numberOfTicketsSold;
    private Boolean createTickets;
}