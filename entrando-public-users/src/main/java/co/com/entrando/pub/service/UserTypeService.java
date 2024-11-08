package co.com.entrando.pub.service;


import co.com.entrando.datos.entity.UserType;

import java.util.Optional;

public interface UserTypeService {
    Optional<UserType> findByType(String type);
}
