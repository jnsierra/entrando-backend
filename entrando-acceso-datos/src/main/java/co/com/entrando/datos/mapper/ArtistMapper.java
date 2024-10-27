package co.com.entrando.datos.mapper;

import co.com.entrando.datos.entity.Artist;
import co.com.entrando.dto.ArtistDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.Set;


@Mapper
public interface ArtistMapper {
    ArtistMapper INSTANCE = Mappers.getMapper(ArtistMapper.class);

    @Mapping(source = "musicBand.id", target = "musicBandId")
    ArtistDto map(Artist artistEntity);

    @Mapping(source = "musicBandId", target = "musicBand.id")
    Artist map(ArtistDto artistDto);

    @Mapping(source = "musicBand.id", target = "musicBandId")
    Set<ArtistDto> map(Set<Artist> artistEntity);
}
