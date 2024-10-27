package co.com.entrando.datos.mapper;

import co.com.entrando.datos.entity.CategoryEvent;
import co.com.entrando.dto.CategoryEventDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.Set;

@Mapper
public interface CategoryEventMapper {
    CategoryEventMapper INSTANCE = Mappers.getMapper(CategoryEventMapper.class);

    CategoryEventDto map(CategoryEvent categoryEvent);

    CategoryEvent map(CategoryEventDto categoryEventDto);

    Set<CategoryEventDto> map(Set<CategoryEvent> categoryEventEntities);
}
