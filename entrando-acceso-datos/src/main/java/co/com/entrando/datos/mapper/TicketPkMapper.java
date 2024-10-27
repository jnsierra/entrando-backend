package co.com.entrando.datos.mapper;

import co.com.entrando.datos.entity.TicketPk;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface TicketPkMapper {
    TicketPkMapper INSTANCE = Mappers.getMapper(TicketPkMapper.class);
    @Mapping(source = "eventId", target = "event.id")
    @Mapping(source = "zoneId", target = "zone.id")
    @Mapping(source = "categoryId", target = "category.id")
    @Mapping(source = "presentationId", target = "presentation.id")
    TicketPk map(Long eventId, Long zoneId, Long categoryId, Long presentationId);
    @Mapping(source = "eventId", target = "event.id")
    @Mapping(source = "zoneId", target = "zone.id")
    @Mapping(source = "categoryId", target = "category.id")
    @Mapping(source = "presentationId", target = "presentation.id")
    @Mapping(source = "numberTicket", target = "numberTicket")
    TicketPk map(Long eventId, Long zoneId, Long categoryId, Long presentationId, Long numberTicket);
}
