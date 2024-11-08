package co.com.entrando.pub.service.impl;

import co.com.entrando.datos.entity.User;
import co.com.entrando.datos.repository.UserRepository;
import co.com.entrando.pub.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import co.com.entrando.enumeration.LOGIN_ACTION;
import co.com.entrando.enumeration.USER_STATE;

import java.util.Optional;

@Service
public class LoginServiceImpl implements LoginService {

    private final UserRepository userRepository;
    @Autowired
    public LoginServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public LOGIN_ACTION validaLogin(String email, String pass) {
        Optional<User> user = userRepository.findByEmail(email);
        if(!user.isPresent()){
            return LOGIN_ACTION.USER_NOT_FOUND;
        }
        if(!this.validateStateUser(user.get())){
            return LOGIN_ACTION.USER_BLOKED;
        }
        if(!this.validatePassword(email, pass, user.get())){
            return LOGIN_ACTION.PASSWORD_INCORRECT;
        }
        if(this.validateChagePass(user.get())){
            return LOGIN_ACTION.SUCCESS_CHANGE_PASSWORD;
        }
        return LOGIN_ACTION.SUCCESS;
    }

    private boolean validateStateUser(User user) {
        if( USER_STATE.ACTIVE.equals(user.getState()) ){
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }

    private boolean validatePassword(String email, String pass, User userEntity) {
        Optional<User> user = userRepository.findByEmailAndPassword(email, pass);
        if(user.isPresent()){
            return Boolean.TRUE;
        }
        //En el caso de ser incorrecto el usuario y contraseña se contara un intento de ingreso
        userRepository.updateAttempts(userEntity.getId(), userEntity.getAttempts() + 1L);
        return Boolean.FALSE;
    }

    private boolean validateChagePass(User user) {
        if("S".equals(user.getChangePassword())){
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }
}
