package co.com.entrando.datos.mapper;

import co.com.entrando.datos.entity.Zone;
import co.com.entrando.dto.ZoneDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.Set;

@Mapper
public interface ZoneMapper {
    ZoneMapper INSTANCE = Mappers.getMapper(ZoneMapper.class);
    @Mapping(source = "categoryId", target = "category.id")
    Zone map(ZoneDto zoneDto);
    @Mapping(source = "category.id", target = "categoryId")
    ZoneDto map(Zone zoneEntity);
    @Mapping(source = "category.id", target = "categoryId")
    Set<ZoneDto> map(Set<Zone> zoneEntity);
}
