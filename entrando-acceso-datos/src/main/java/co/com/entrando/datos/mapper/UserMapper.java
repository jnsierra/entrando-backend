package co.com.entrando.datos.mapper;

import co.com.entrando.datos.entity.User;
import co.com.entrando.dto.UsuarioDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);
    UsuarioDto map(User userEntity);
    User map(UsuarioDto userDto);
}
