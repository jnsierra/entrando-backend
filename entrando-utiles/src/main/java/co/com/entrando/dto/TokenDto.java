package co.com.entrando.dto;

import co.com.entrando.enumeration.LOGIN_ACTION;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TokenDto {
    private String token;
    private Integer time;
    private String mensaje;
    private LOGIN_ACTION loginAction;
}
