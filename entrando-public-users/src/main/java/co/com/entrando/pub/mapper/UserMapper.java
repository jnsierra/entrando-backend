package co.com.entrando.pub.mapper;

import co.com.entrando.datos.entity.User;
import co.com.entrando.dto.UsuarioDto;
import co.com.entrando.enumeration.USER_STATE;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);
    UsuarioDto map(User userEntity);
    User map(UsuarioDto userDto);
    @Mapping(source = "email", target = "email")
    @Mapping(source = "name", target = "name")
    @Mapping(source = "attempts", target = "attempts")
    @Mapping(source = "changePassword", target = "changePassword")
    @Mapping(source = "state", target = "state")
    @Mapping(source = "code", target = "code")
    UsuarioDto map(String email, String name, String attempts, String changePassword, USER_STATE state, String code);
}