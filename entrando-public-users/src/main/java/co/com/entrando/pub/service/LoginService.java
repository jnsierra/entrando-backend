package co.com.entrando.pub.service;

import co.com.entrando.enumeration.LOGIN_ACTION;

public interface LoginService {
    LOGIN_ACTION validaLogin(String email, String pass);
}
