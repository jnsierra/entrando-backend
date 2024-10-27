package co.com.entrando.datos.mapper;

import co.com.entrando.datos.entity.Category;
import co.com.entrando.dto.CategoryDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.Set;

@Mapper
public interface CategoryMapper {
    CategoryMapper INSTANCE = Mappers.getMapper(CategoryMapper.class);

    CategoryDto map(Category categoryDto);

    Category map(CategoryDto categoryDto);

    Set<CategoryDto> map(Set<Category> categoryDto);
}
