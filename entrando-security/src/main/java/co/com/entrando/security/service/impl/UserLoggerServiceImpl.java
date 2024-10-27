package co.com.entrando.security.service.impl;

import co.com.entrando.security.service.UserLoggerService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class UserLoggerServiceImpl implements UserLoggerService {
    @Override
    public String getUserLogger() {
        String usuario = "";
        try {
            usuario = SecurityContextHolder.getContext().getAuthentication().getName();

        }catch (Exception e){
            usuario = "publico";
        }
        return usuario;
    }
}
