package co.com.entrando.datos.service.impl;


import co.com.entrando.datos.entity.User;
import co.com.entrando.datos.entity.UserType;
import co.com.entrando.datos.repository.UserRepository;
import co.com.entrando.datos.service.UserService;
import co.com.entrando.datos.service.UserTypeService;
import co.com.entrando.exception.BusinessException;
import co.com.entrando.exception.enumeration.TYPE_EXCEPTION;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserTypeService userTypeService;
    @Autowired
    public UserServiceImpl(UserRepository userRepository, UserTypeService userTypeService) {
        this.userRepository = userRepository;
        this.userTypeService = userTypeService;
    }
    @Override
    public User save(User userEntity) {
        return userRepository.save(userEntity);
    }
    @Override
    @Transactional
    @PreAuthorize("#email == authentication.name")
    public boolean updatePassword(String email, String password) {
        if( userRepository.updatePassword(email, password) != 1 ){
            throw new BusinessException(1L, TYPE_EXCEPTION.ERROR, String.format("Error al actualizar la contraseña del usuario %s",email));
        }
        return true;
    }
    @Override
    @Transactional
    public boolean deleteUserType(String email) {
        Optional<User> user = userRepository.findByEmail(email);
        if(user.isEmpty()){
            return false;
        }
        user.get().getUserTypes().forEach(item -> user.get().removeUserType(item));
        return true;
    }

    @Override
    @Transactional
    public boolean addUserType(String email, String userType) {
        Optional<User> user = userRepository.findByEmail(email);
        if(user.isEmpty()){
            return false;
        }
        Optional<UserType> userTypeObj = userTypeService.getByType(userType);
        if(userTypeObj.isEmpty()){
            return false;
        }
        user.get().addUserType(userTypeObj.get());
        return true;
    }
}