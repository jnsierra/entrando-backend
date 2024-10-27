package co.com.entrando.datos.mapper;

import co.com.entrando.datos.entity.Presentation;
import co.com.entrando.dto.PresentationDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.Set;

@Mapper
public interface PresentationMapper {
    PresentationMapper INSTANCE = Mappers.getMapper(PresentationMapper.class);
    @Mapping(source = "eventId", target = "event.id")
    Presentation map(PresentationDto presentationDto);
    @Mapping(source = "event.id", target = "eventId")
    PresentationDto map(Presentation presentation);
    @Mapping(source = "event.id", target = "eventId")
    Set<PresentationDto> map(Set<Presentation> presentation);
}