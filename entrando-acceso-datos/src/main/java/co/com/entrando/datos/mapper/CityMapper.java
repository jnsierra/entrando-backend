package co.com.entrando.datos.mapper;

import co.com.entrando.datos.entity.City;
import co.com.entrando.dto.CityDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.Set;

@Mapper
public interface CityMapper {
    CityMapper INSTANCE = Mappers.getMapper(CityMapper.class);

    @Mapping(source = "cityPk.code", target = "code")
    @Mapping(source = "cityPk.departmentCode", target = "departmentCode")
    CityDto map(City city);

    @Mapping(source = "cityPk.code", target = "code")
    @Mapping(source = "cityPk.departmentCode", target = "departmentCode")
    Set<CityDto> map(Set<City> cities);
}
