package co.com.entrando.datos.mapper;

import co.com.entrando.datos.entity.Country;
import co.com.entrando.dto.CountryDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.Set;

@Mapper
public interface CountryMapper {

    CountryMapper  INSTANCE = Mappers.getMapper(CountryMapper.class);
    @Mapping(source = "code", target = "code")
    @Mapping(source = "name", target = "name")
    Set<CountryDto> map(Set<Country> country);
}
