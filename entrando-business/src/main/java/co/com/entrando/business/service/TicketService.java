package co.com.entrando.business.service;

import co.com.entrando.dto.TicketViewDto;
import co.com.entrando.dto.responses.GenericResponse;
import co.com.entrando.dto.ticket.BuyTicket;
import co.com.entrando.dto.ticket.ConfirmBuyTicket;

public interface TicketService {
    Boolean generateTicket(Long idEvent, Long presentation);
    GenericResponse<ConfirmBuyTicket> buyTicket(BuyTicket buyTicket);
    GenericResponse<TicketViewDto> getTiketsByUser();
}
