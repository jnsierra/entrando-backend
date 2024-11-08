package co.com.entrando.pub.service;


import co.com.entrando.dto.TokenDto;

import java.util.Optional;

public interface TokenService {
    Optional<TokenDto> generateTokenUser(String email);
}
