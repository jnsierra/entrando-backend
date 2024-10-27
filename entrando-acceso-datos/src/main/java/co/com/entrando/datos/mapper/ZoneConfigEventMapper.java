package co.com.entrando.datos.mapper;

import co.com.entrando.datos.entity.ZoneConfigEvent;
import co.com.entrando.dto.ZoneConfigEventDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.Set;

@Mapper
public interface ZoneConfigEventMapper {
    ZoneConfigEventMapper INSTANCE = Mappers.getMapper(ZoneConfigEventMapper.class);
    @Mapping(source = "zone.id", target = "zoneId")
    @Mapping(source = "configEvent.id", target = "configEventId")
    ZoneConfigEventDto map(ZoneConfigEvent zoneConfigEvent);
    @Mapping(source = "zoneId", target = "zone.id")
    @Mapping(source = "configEventId", target = "configEvent.id")
    ZoneConfigEvent map(ZoneConfigEventDto zoneConfigEvent);

    @Mapping(source = "zone.id", target = "zoneId")
    @Mapping(source = "configEvent.id", target = "configEventId")
    Set<ZoneConfigEventDto> map(Set<ZoneConfigEvent> zoneConfigEvent);

}