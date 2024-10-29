package co.com.entrando.business.controller;


import co.com.entrando.business.service.TicketService;
import co.com.entrando.dto.TicketViewDto;
import co.com.entrando.dto.responses.GenericResponse;
import co.com.entrando.dto.ticket.BuyTicket;
import co.com.entrando.dto.ticket.ConfirmBuyTicket;
import co.com.entrando.exception.enumeration.TYPE_EXCEPTION;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v.1/ticket")
@Slf4j
public class TicketController {

    private final TicketService ticketService;
    @Autowired
    public TicketController(TicketService ticketService) {
        this.ticketService = ticketService;
    }
    @GetMapping("/generate/event/{event_id}/presentation/{presentation_id}")
    public ResponseEntity<GenericResponse<String>> generate(@PathVariable(value = "event_id") Long idEvent, @PathVariable(value = "presentation_id") Long idPresentation ) {
        boolean resp = ticketService.generateTicket(idEvent, idPresentation);
        if(!resp){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(GenericResponse.<String>builder()
                        .code(1L)
                        .message("Boletas Generadas Correctamente")
                        .type(TYPE_EXCEPTION.SUCCESS)
                .build());
    }
    @PostMapping("/buy")
    public ResponseEntity<GenericResponse<ConfirmBuyTicket>> buyTickets(@RequestBody BuyTicket buyTicket){
        log.debug("Buy Ticket {}",buyTicket);
        return ResponseEntity.ok(ticketService.buyTicket(buyTicket));
    }

    @GetMapping("/by/user")
    public ResponseEntity<GenericResponse<TicketViewDto>> getTickets() {
        return ResponseEntity.ok(ticketService.getTiketsByUser());
    }
}