package co.com.entrando.datos.mapper;

import co.com.entrando.datos.entity.ConfigEvent;
import co.com.entrando.dto.ConfigEventDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.Set;

@Mapper
public interface ConfigEventMapper {
    ConfigEventMapper INSTANCE = Mappers.getMapper(ConfigEventMapper.class);

    @Mapping(source = "eventId", target = "event.id")
    @Mapping(source = "presentationId", target = "presentation.id")
    ConfigEvent map(ConfigEventDto configEventDto);

    @Mapping(source = "event.id", target = "eventId")
    @Mapping(source = "presentation.id", target = "presentationId")
    ConfigEventDto map(ConfigEvent configEvent);

    @Mapping(source = "event.id", target = "eventId")
    @Mapping(source = "presentation.id", target = "presentationId")
    Set<ConfigEventDto> map(Set<ConfigEvent> configEvent);
}