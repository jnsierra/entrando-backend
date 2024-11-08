package co.com.entrando.pub.mapper;

import co.com.entrando.datos.entity.Presentation;
import co.com.entrando.dto.PresentationDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.Set;

@Mapper
public interface PresentationMapper {
    PresentationMapper INSTANCE = Mappers.getMapper(PresentationMapper.class);
    @Mapping(source = "event.id", target = "eventId")
    PresentationDto map(Presentation presentationEntity);
    @Mapping(source = "event.id", target = "eventId")
    Set<PresentationDto> map(Set<Presentation> presentations);
}
