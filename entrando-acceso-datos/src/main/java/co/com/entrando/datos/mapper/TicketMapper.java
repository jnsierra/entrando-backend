package co.com.entrando.datos.mapper;


import co.com.entrando.datos.entity.Ticket;
import co.com.entrando.dto.TicketDto;
import co.com.entrando.dto.TicketViewDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.Set;

@Mapper
public interface TicketMapper {
    TicketMapper INSTANCE = Mappers.getMapper(TicketMapper.class);
    @Mapping(source = "eventId", target = "id.eventId")
    @Mapping(source = "zoneId", target = "id.zoneId")
    @Mapping(source = "categoryId", target = "id.categoryId")
    @Mapping(source = "presentationId", target = "id.presentationId")
    @Mapping(source = "numberTicket", target = "id.numberTicket")
    Ticket map(TicketDto ticketDto);
    @Mapping(source = "id.eventId", target = "eventId")
    @Mapping(source = "id.zoneId", target = "zoneId")
    @Mapping(source = "id.categoryId", target = "categoryId")
    @Mapping(source = "id.presentationId", target = "presentationId")
    @Mapping(source = "id.numberTicket", target = "numberTicket")
    TicketDto map(Ticket ticketEntity);
    @Mapping(source = "id.eventId", target = "eventId")
    @Mapping(source = "id.zoneId", target = "zoneId")
    @Mapping(source = "id.categoryId", target = "categoryId")
    @Mapping(source = "id.presentationId", target = "presentationId")
    @Mapping(source = "id.numberTicket", target = "numberTicket")
    Set<TicketDto> map(Set<Ticket> ticketEntity);

    @Mapping(source = "id.eventId", target = "eventId")
    @Mapping(source = "id.zoneId", target = "zoneId")
    @Mapping(source = "id.categoryId", target = "categoryId")
    @Mapping(source = "id.presentationId", target = "presentationId")
    @Mapping(source = "id.numberTicket", target = "numberTicket")
    Set<TicketViewDto> mapToView(Set<Ticket> ticketEntity);

    @Mapping(source = "id.eventId", target = "eventId")
    @Mapping(source = "id.zoneId", target = "zoneId")
    @Mapping(source = "id.categoryId", target = "categoryId")
    @Mapping(source = "id.presentationId", target = "presentationId")
    @Mapping(source = "id.numberTicket", target = "numberTicket")
    TicketViewDto mapToView(Ticket ticketEntity);
}
