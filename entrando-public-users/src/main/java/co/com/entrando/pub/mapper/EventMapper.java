package co.com.entrando.pub.mapper;


import co.com.entrando.datos.entity.Event;
import co.com.entrando.dto.EventDto;
import co.com.entrando.dto.EventMoreInfoDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.Set;

@Mapper(uses = {PresentationMapper.class, ConfigEventMapper.class})
public interface EventMapper {
    EventMapper INSTANCE = Mappers.getMapper(EventMapper.class);
    @Mapping(source = "cityCode", target = "city.cityPk.code")
    @Mapping(source = "departmentCode", target = "city.cityPk.departmentCode")
    @Mapping(source = "eventStatus", target = "eventStatus")
    @Mapping(source = "categoryEventId", target = "categoryEvent.id")
    Event map(EventDto eventDto);
    @Mapping(source = "city.cityPk.code", target = "cityCode")
    @Mapping(source = "city.cityPk.departmentCode", target = "departmentCode")
    @Mapping(source = "categoryEvent.id", target = "categoryEventId")
    EventDto map(Event event);
    @Mapping(source = "city.cityPk.code", target = "cityCode")
    @Mapping(source = "city.cityPk.departmentCode", target = "departmentCode")
    @Mapping(source = "categoryEvent.id", target = "categoryEventId")
    Set<EventDto> map(Set<Event> department);
    @Mapping(source = "city.name", target = "cityName")
    @Mapping(source = "city.department.name", target = "departmentName")
    @Mapping(source = "city.department.country.name", target = "countryName")
    @Mapping(source = "categoryEvent.name", target = "categoryEventName")
    EventMoreInfoDto mapMoreInfo(Event event);
}