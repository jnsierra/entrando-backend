package co.com.entrando.datos.mapper;

import co.com.entrando.datos.entity.MusicBand;
import co.com.entrando.dto.MusicBandDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.Set;

@Mapper
public interface MusicBandMapper {
    MusicBandMapper INSTANCE = Mappers.getMapper(MusicBandMapper.class);
    @Mapping(source = "presentationId", target = "presentation.id")
    MusicBand map(MusicBandDto musicBandDto);
    @Mapping(source = "presentation.id", target = "presentationId")
    MusicBandDto map(MusicBand musicBand);
    @Mapping(source = "presentation.id", target = "presentationId")
    Set<MusicBandDto> map(Set<MusicBand> musicBand);
}
