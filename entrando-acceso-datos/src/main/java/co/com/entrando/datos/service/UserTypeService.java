package co.com.entrando.datos.service;

import co.com.entrando.datos.entity.UserType;

import java.util.Optional;

public interface UserTypeService {
    Optional<UserType> getByType(String type);
}
