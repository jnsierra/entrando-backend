package co.com.entrando.datos.service.impl;

import co.com.entrando.datos.entity.Ticket;
import co.com.entrando.datos.entity.TicketId;
import co.com.entrando.datos.filtering.JoinEntity;
import co.com.entrando.datos.filtering.SearchRequest;
import co.com.entrando.datos.filtering.SearchSpecification;
import co.com.entrando.datos.mapper.TicketMapper;
import co.com.entrando.datos.repository.TicketRepository;
import co.com.entrando.datos.service.ConfigEventService;
import co.com.entrando.datos.service.TicketService;
import co.com.entrando.datos.service.ZoneConfigEventService;
import co.com.entrando.dto.TicketViewDto;
import co.com.entrando.dto.responses.GenericQuery;
import co.com.entrando.exception.BusinessException;
import co.com.entrando.exception.enumeration.TYPE_EXCEPTION;
import co.com.entrando.security.service.UserLoggerService;
import co.com.entrando.enumeration.StatusTicket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.*;
import java.util.function.BiFunction;
import java.util.stream.Collectors;

@Service
public class TicketServiceImpl implements TicketService {
    private TicketRepository ticketRepository;
    private final ConfigEventService configEventService;
    private final ZoneConfigEventService zoneConfigEventService;
    private final UserLoggerService userLoggerService;
    private final BiFunction<SearchSpecification<Ticket>, Pageable, Page<Ticket>> findAllTicket = (specification, pageable) -> ticketRepository.findAll(specification, pageable);

    @Autowired
    public TicketServiceImpl(TicketRepository ticketRepository, UserLoggerService userLoggerService, ConfigEventService configEventService, ZoneConfigEventService zoneConfigEventService) {
        this.ticketRepository = ticketRepository;
        this.userLoggerService = userLoggerService;
        this.configEventService = configEventService;
        this.zoneConfigEventService = zoneConfigEventService;
    }

    @Override
    public Ticket save(Ticket ticket) {
        return ticketRepository.save(ticket);
    }

    @Override
    public Optional<Ticket> getById(TicketId id) {
        return ticketRepository.findById(id);
    }

    @Override
    public Set<TicketViewDto> getByEventIdAndPresentationId(Long eventId, Long presentationId, Integer records, Integer page) {
        Pageable pageable = PageRequest.of(page, records);
        List<Ticket> result = ticketRepository.getByEventIdAndPresentationId(eventId, presentationId, pageable);
        return result.stream().map(TicketMapper.INSTANCE::mapToView)
                .collect(Collectors.toSet());
    }

    @Override
    public Integer countByEventIdAndPresentationId(Long eventId, Long presentationId) {
        return ticketRepository.countByEventIdAndPresentationId(eventId, presentationId);
    }

    @Override
    public Integer updateState(StatusTicket state, Long eventId, Long zoneId, Long categoryId, Long presentationId, Long numberTicket, String confirmationNumber, String user) {
        return ticketRepository.updateState(state.toString(), eventId, zoneId, categoryId, presentationId, numberTicket, confirmationNumber, user);
    }

    @Override
    @Transactional
    public Optional<Ticket> buyTicket(StatusTicket state, Long eventId, Long zoneId, Long categoryId, Long presentationId, Long numberTicket) {
        Integer update = this.updateState(state, eventId, zoneId, categoryId, presentationId, numberTicket, this.getAlphaNumericString(40), userLoggerService.getUserLogger());
        if (update != 1) {
            throw new BusinessException(1L, TYPE_EXCEPTION.ERROR, "Se genero actualizacion a mas de un registro");
        }
        Optional<Ticket> ticketEntity = this.getById(TicketId.builder()
                .eventId(eventId.intValue())
                .zoneId(zoneId.intValue())
                .categoryId(categoryId.intValue())
                .presentationId(presentationId.intValue())
                .numberTicket(numberTicket.intValue())
                .build());
        if (!ticketEntity.isPresent()) {
            throw new BusinessException(1L, TYPE_EXCEPTION.ERROR, "No se encontro tickete comprado");
        }
        Optional<Long> idConfigEvent = configEventService.recordSale(eventId, presentationId);
        if (!idConfigEvent.isPresent()) {
            throw new BusinessException(1L, TYPE_EXCEPTION.ERROR, "Imposible actualizar el consolidado en config_event_service");
        }
        boolean valida = zoneConfigEventService.recordSale(zoneId, idConfigEvent.get());
        if (!valida) {
            throw new BusinessException(1L, TYPE_EXCEPTION.ERROR, "Imposible actualizar el consolidado por zona");
        }
        return ticketEntity;
    }

    @Override
    public Set<Ticket> getByEmailAndEventAndPresentation(String email, Long eventId, Long presentationId) {
        return ticketRepository.getByEmailAndEventAndPresentation(email, eventId, presentationId);
    }

    @Override
    @Transactional
    public GenericQuery<TicketViewDto> searchTickets(SearchRequest request) {
        BiFunction<SearchSpecification<Ticket>, Pageable, GenericQuery<TicketViewDto>> find = findAllTicket.andThen(this::mapper)
                .andThen(item -> filterByJoins(item, request.getJoins()));
        return find.apply(new SearchSpecification<>(request), SearchSpecification.getPageable(request.getPage(), request.getSize()));
    }

    private GenericQuery<TicketViewDto> filterByJoins(GenericQuery<TicketViewDto> data, List<JoinEntity> joins) {
        Set<TicketViewDto> temp = data.getResults();
        for (JoinEntity join : joins) {
            temp = temp.stream().filter(ticket -> {
                if ("event".equalsIgnoreCase(join.getEntity()) && ticket.getEventId().equals(join.getFieldType().parse(join.getValue())) ||
                        "presentation".equalsIgnoreCase(join.getEntity()) && ticket.getPresentationId().equals(join.getFieldType().parse(join.getValue()))
                ) {
                    return true;
                }
                return false;
            }).collect(Collectors.toSet());
        }
        data.setResults(temp);
        return data;
    }

    private GenericQuery<TicketViewDto> mapper(Page<Ticket> ticketEntities) {
        return GenericQuery.<TicketViewDto>builder()
                .results(TicketMapper.INSTANCE.mapToView(new HashSet<>(ticketEntities.getContent())))
                .totalRecords((int) ticketEntities.getTotalElements())
                .page(ticketEntities.getPageable().getPageNumber())
                .records(ticketEntities.getContent().size())
                .build();
    }


    private String getAlphaNumericString(int n) {
        // choose a Character random from this String
        String alphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        alphaNumericString += "0123456789";
        alphaNumericString += "abcdefghijklmnopqrstuvxyz";

        // create StringBuffer size of AlphaNumericString
        StringBuilder sb = new StringBuilder(n);
        Random rand;
        try {
            rand = SecureRandom.getInstanceStrong();
        } catch (NoSuchAlgorithmException e) {
            throw new BusinessException(1L, TYPE_EXCEPTION.ERROR, "Error al generar la instancia del random", e);
        }
        for (int i = 0; i < n; i++) {

            // generate a random number between
            // 0 to AlphaNumericString variable length
            int index = rand.nextInt(0, alphaNumericString.length());

            // add Character one by one in end of sb
            sb.append(alphaNumericString
                    .charAt(index));
        }
        return sb.toString();
    }
}
