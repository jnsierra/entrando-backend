package co.com.entrando.pub.mapper;


import co.com.entrando.datos.entity.EventImages;
import co.com.entrando.dto.EventImagesDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.Set;

@Mapper
public interface EventImagesMapper {
    EventImagesMapper INSTANCE = Mappers.getMapper(EventImagesMapper.class);
    @Mapping(source = "event.id", target = "eventId")
    EventImagesDto map(EventImages eventImages);
    @Mapping(source = "eventId", target = "event.id")
    EventImages map(EventImagesDto eventImagesDto);
    @Mapping(source = "event.id", target = "eventId")
    Set<EventImagesDto> map(Set<EventImages> eventImages);
}
