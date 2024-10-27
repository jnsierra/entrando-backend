package co.com.entrando.datos.mapper;

import co.com.entrando.datos.entity.Department;
import co.com.entrando.dto.DepartmentDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.Set;

@Mapper
public interface DepartmentMapper {
    DepartmentMapper  INSTANCE = Mappers.getMapper(DepartmentMapper.class);
    Set<DepartmentDto> map(Set<Department> department);

    @Mapping(source = "country.code", target = "countryCode")
    DepartmentDto map(Department department);
}