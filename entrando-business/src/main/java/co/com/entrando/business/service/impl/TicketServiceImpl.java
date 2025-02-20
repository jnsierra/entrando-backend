package co.com.entrando.business.service.impl;

import co.com.entrando.business.client.TicketClient;
import co.com.entrando.business.service.*;
import co.com.entrando.dto.TicketDto;
import co.com.entrando.dto.TicketViewDto;
import co.com.entrando.dto.ZoneConfigEventDto;
import co.com.entrando.dto.ZoneDto;
import co.com.entrando.dto.client.FieldType;
import co.com.entrando.dto.client.FilterRequest;
import co.com.entrando.dto.client.Operator;
import co.com.entrando.dto.client.SearchRequest;
import co.com.entrando.dto.responses.GenericQuery;
import co.com.entrando.dto.responses.GenericResponse;
import co.com.entrando.dto.ticket.BuyTicket;
import co.com.entrando.dto.ticket.ConfirmBuyTicket;
import co.com.entrando.security.service.UserLoggerService;
import co.com.entrando.enumeration.EventStatus;
import co.com.entrando.enumeration.StatusTicket;
import co.com.entrando.exception.BusinessException;
import co.com.entrando.exception.enumeration.TYPE_EXCEPTION;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.function.*;
import java.util.stream.Collectors;

@Service
@Slf4j
public class TicketServiceImpl implements TicketService {
    private EventService eventService;
    private ZoneConfigEventService zoneConfigEventService;
    private ConfigEventService configEventService;
    private ZoneService zoneService;
    private TicketClient ticketClient;
    private UserLoggerService userLoggerService;
    private EmailService emailService;
    private QRGeneratorService qRGeneratorService;
    private GenerateTemplatesEmailServiceImpl generateTemplatesEmailService;
    private final BiFunction<Long, Long, Set<ZoneConfigEventDto>> functionGenerateTickets = (eventId, presentationId) -> zoneConfigEventService.getByIdEventAndPresentation(eventId, presentationId);
    private final LongFunction<ZoneDto> functionGetZone = zoneId -> zoneService.getById(zoneId);
    private final Predicate<String> isGenerateCode = codeConfirmation -> qRGeneratorService.generateQRCodeImage(codeConfirmation);
    private Function<Optional<TicketDto>, ConfirmBuyTicket> functionCreateObjConfirm = ticket -> {
        log.info("PURCHASED-TICKET|{}|{}|{}", ticket.isPresent(), (ticket.isPresent() ? ticket.get().getNumberTicket() : -1L), ticket.isPresent() ? ticket.get().getEventId() : -1L);
        return ConfirmBuyTicket.builder()
                .confirmNumberTicket(ticket.get().getConfirmationNumber())
                .numberTicket(ticket.get().getNumberTicket())
                .build();
    };
    private final UnaryOperator<TicketDto> funcGetTicket = ticket ->
            ticketClient.getByIdNumber(ticket.getEventId(), ticket.getZoneId(), ticket.getCategoryId(), ticket.getPresentationId(), ticket.getNumberTicket());
    private final Predicate<TicketDto> isTicketAvailable = ticket -> (Objects.nonNull(ticket) && StatusTicket.CREATED.equals(ticket.getState()));
    private final BiFunction<Long, Long, Set<TicketDto>> funcGenerateTicketObj = (idEvent, idPresentation) ->
            functionGenerateTickets.apply(idEvent, idPresentation).stream().map(zoneConfigEventDto -> generateTickets(zoneConfigEventDto, idEvent, idPresentation))
                    .flatMap(Collection::stream)
                    .collect(Collectors.toSet());
    private Function<String, SearchRequest> funcObjectQuery = user -> SearchRequest
            .builder()
            .filters(List.of(
                    FilterRequest.builder()
                            .key("userEmail")
                            .operator(Operator.EQUAL)
                            .fieldType(FieldType.STRING)
                            .value(user)
                            .build()
            ))
            .build();
    private final BiPredicate<Long, Long> isEqualParameterConfigEvent = (zoneConfigEvent, configEvent) -> zoneConfigEvent.equals(configEvent);
    private final Predicate<Set<ZoneConfigEventDto>> isConfirmCreate = configs ->  {
        configs.stream().
                forEach(item -> this.zoneConfigEventService.updateCreateTickets(item.getId()));
        return true;
    };
    private final LongPredicate isConfirmEvent = eventId -> eventService.changeStatusEventWaiting(eventId);
    @Autowired
    public TicketServiceImpl(ZoneConfigEventService zoneConfigEventService, ZoneService zoneService
            , TicketClient ticketClient, UserLoggerService userLoggerService, QRGeneratorService qRGeneratorService
            , GenerateTemplatesEmailServiceImpl generateTemplatesEmailService, EmailService emailService
            , ConfigEventService configEventService, EventService eventService) {
        this.zoneConfigEventService = zoneConfigEventService;
        this.zoneService = zoneService;
        this.ticketClient = ticketClient;
        this.userLoggerService = userLoggerService;
        this.qRGeneratorService = qRGeneratorService;
        this.generateTemplatesEmailService = generateTemplatesEmailService;
        this.emailService = emailService;
        this.configEventService = configEventService;
        this.eventService = eventService;
    }
    @Override
    public Boolean generateTicket(Long idEvent, Long idPresentation) {
        isValidProcess(idEvent, idPresentation);
        Set<ZoneConfigEventDto> configs = functionGenerateTickets.andThen(this::validateSet).apply(idEvent, idPresentation);
        if(!funcGenerateTicketObj.andThen(this::generateTicketIterate).apply(idEvent, idPresentation)){
            return false;
        }
        return isConfirmCreate.and(item -> this.isConfirmEvent(idEvent)).test(configs);
    }

    private boolean isConfirmEvent(Long idEvent) {
        return isConfirmEvent.test(idEvent);
    }
    private void isValidProcess(Long idEvent, Long idPresentation) {
        //Valido si el evento ya creo sus boletas
        eventService.getEventById(idEvent)
                .filter(Predicate.not(EventStatus.CREATED::equals))
                .orElseThrow(()-> new BusinessException(1L, TYPE_EXCEPTION.ERROR, "Evento en estado diferente a CREATED (imposible generar tickets)"));
        //Valido si existe configuracion para la generacion de tickets
        Optional.ofNullable(functionGenerateTickets.apply(idEvent, idPresentation))
                .orElseThrow(() -> new BusinessException(1L, TYPE_EXCEPTION.ERROR, "No existen configuraciones de zona para la generacion de tickets"));

        Set<ZoneConfigEventDto> configs = functionGenerateTickets.andThen(this::validateSet).apply(idEvent, idPresentation);
        if (Objects.isNull(configs) || configs.isEmpty()) {
            throw new BusinessException(2L, TYPE_EXCEPTION.ERROR, "No existe ninguna zona por generar tickets o las actuales ya tienen tickets generados");
        }
        //Valido si tengo config event
        if(!configEventService.existsConfigEvent(idEvent, idPresentation)){
            throw new BusinessException(2L, TYPE_EXCEPTION.ERROR, "No existe configuracion para el evento");
        }
        long sumaTickets = configs.stream().mapToLong(ZoneConfigEventDto::getNumberOfTickets).sum();
        if(!isEqualParameterConfigEvent.test(sumaTickets, configEventService.getNumberOfTicketsEventAndPresentation(idEvent, idPresentation))){
            throw new BusinessException(2L, TYPE_EXCEPTION.ERROR, "No coinciden la parametrizacion de la configuracion del evento y la configuracion de las zonas del evento");
        }

    }

    public Set<ZoneConfigEventDto> validateSet(Set<ZoneConfigEventDto> zoneConfig) {
        return zoneConfig.stream()
                .map(item -> {
                    if (Objects.isNull(item.getCreateTickets())) {
                        item.setCreateTickets(Boolean.FALSE);
                    }
                    return item;
                })
                .filter(Predicate.not(ZoneConfigEventDto::getCreateTickets))
                .collect(Collectors.toSet());
    }
    @Override
    public GenericResponse<ConfirmBuyTicket> buyTicket(BuyTicket buyTicket) {
        if (!isTicketsAvailability.test(buyTicket)) {
            return GenericResponse.<ConfirmBuyTicket>builder()
                    .code(1L)
                    .type(TYPE_EXCEPTION.ERROR)
                    .message("Tickets no disponibles")
                    .build();
        }
        Set<ConfirmBuyTicket> confirmations = createTicket(buyTicket).stream()
                .peek(ticketDto -> log.debug("Ticket pre buy {}", ticketDto))
                .map(this::buyTicket)
                .peek(ticketDto -> log.debug("Ticket buy {}", ticketDto))
                .filter(Optional::isPresent)
                .map(this::generateQR)
                .map(functionCreateObjConfirm)
                .map(this::sendEmailConfirmation)
                .collect(Collectors.toSet());
        return GenericResponse.<ConfirmBuyTicket>builder()
                .code(0L)
                .type(TYPE_EXCEPTION.SUCCESS)
                .message("Tickets Comprados")
                .data(confirmations)
                .build();
    }

    private ConfirmBuyTicket sendEmailConfirmation(ConfirmBuyTicket confirmBuyTicket) {
        String htmlTemplate = Optional.ofNullable(generateTemplatesEmailService.buyTicket(confirmBuyTicket.getConfirmNumberTicket()))
                .orElseThrow(() -> new BusinessException(1L, TYPE_EXCEPTION.ERROR, "Error al generar el template html para el correo"));
        Boolean isSending = Optional.ofNullable(emailService.sendHtmlMessage(userLoggerService.getUserLogger(), "Compra Ticket", htmlTemplate))
                .orElseThrow(() -> new BusinessException(1L, TYPE_EXCEPTION.ERROR, "Error al enviar notificacion de confirmacion de compra"));
        isSendingNotification(confirmBuyTicket, isSending);
        return confirmBuyTicket;
    }

    private void isSendingNotification(ConfirmBuyTicket confirmBuyTicket, boolean sending) {
        //TODO persistir en la base de datos que se envio la notificación correctamente
    }

    private Optional<TicketDto> generateQR(Optional<TicketDto> ticket) {
        log.info("GENERATE-TICKET|{}|{}|{}", ticket.isPresent(), (ticket.isPresent() ? ticket.get().getNumberTicket() : -1L), ticket.isPresent() ? ticket.get().getEventId() : -1L);
        if (isGenerateCode.test(ticket.orElseThrow(() -> new BusinessException(1L, TYPE_EXCEPTION.ERROR, "")).getConfirmationNumber())) {
            log.info("GENERATE-QR|true");
            return ticket;
        }
        return Optional.empty();
    }

    @Override
    public GenericResponse<TicketViewDto> getTiketsByUser() {
        Function<String, GenericResponse<TicketViewDto>> ticketsFunction = funcObjectQuery
                .andThen(ticketClient::search)
                .andThen(this::mapperTicket);
        return ticketsFunction.apply(userLoggerService.getUserLogger());
    }
    public GenericResponse<TicketViewDto> mapperTicket(GenericQuery<TicketViewDto> tickets) {
        return GenericResponse.<TicketViewDto>builder()
                .data(tickets.getResults())
                .code(1L)
                .type(TYPE_EXCEPTION.SUCCESS)
                .message("Consulta ejecutada exitosamente")
                .build();
    }

    private final Function<TicketDto, ResponseEntity<TicketDto>> functBuyTicket = ticket -> ticketClient.buyTicket(StatusTicket.RESERVED
            , ticket.getEventId()
            , ticket.getZoneId()
            , ticket.getCategoryId()
            , ticket.getPresentationId()
            , ticket.getNumberTicket());

    private Optional<TicketDto> buyTicket(TicketDto buyTicket) {
        return (Optional<TicketDto>) functBuyTicket
                .andThen(response -> HttpStatus.NO_CONTENT.equals(response.getStatusCode()) ? Optional.empty() : Optional.of(response.getBody()))
                .apply(buyTicket);
    }

    public Set<TicketDto> createTicket(BuyTicket buyTicket) {
        return buyTicket.getNumberTickets().stream()
                .map(ticketNum -> TicketDto.builder()
                        .eventId(buyTicket.getEventId())
                        .zoneId(buyTicket.getZoneId())
                        .categoryId(buyTicket.getCategoryId())
                        .presentationId(buyTicket.getPresentationId())
                        .numberTicket(ticketNum)
                        .build())
                .collect(Collectors.toSet());
    }

    private final Predicate<BuyTicket> isTicketsAvailability = buyTicket -> createTicket(buyTicket).stream()
            .peek(ticket -> log.debug("ticket pre validated {}", ticket))
            .filter(Predicate.not(this::validateTicket))
            .peek(ticket -> log.debug("ticket validated {}", ticket))
            .count() == 0;

    private boolean validateTicket(TicketDto ticketDto) {
        return isTicketAvailable.test(funcGetTicket.apply(ticketDto));
    }
    private Set<TicketDto> generateTickets(ZoneConfigEventDto zoneConfigEventDto, Long eventId, Long presentationId) {
        Set<TicketDto> ticketsDto = new HashSet<>();
        ZoneDto zone = functionGetZone.apply(zoneConfigEventDto.getZoneId());
        for (long i = 1; i <= zoneConfigEventDto.getNumberOfTickets(); i++) {
            ticketsDto.add(TicketDto.builder()
                    .eventId(eventId)
                    .zoneId(zone.getId())
                    .categoryId(zone.getCategoryId())
                    .presentationId(presentationId)
                    .numberTicket(i)
                    .state(StatusTicket.CREATED)
                    .build());
        }
        return ticketsDto;
    }

    private boolean generateTicketIterate(Set<TicketDto> tickets) {
        tickets.stream()
                .forEach(this::saveTicket);
        return true;
    }

    private void saveTicket(TicketDto ticketDto) {
        ticketClient.save(ticketDto);
    }
}
