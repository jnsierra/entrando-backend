package co.com.entrando.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ZoneConfigEventViewDto {
    private Long id;
    private ZoneViewDto zone;
    private Long configEventId;
    private Long numberOfTickets;
    private BigDecimal cost;
    private Long numberOfTicketsSold;
    private Boolean createTickets;
}