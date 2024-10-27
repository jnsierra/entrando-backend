package co.com.entrando.datos.config;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class CustomKeycloakLogoutHandler implements LogoutHandler {

    private final OAuth2AuthorizedClientService clientService;
    private final RestTemplate restTemplate;

    public CustomKeycloakLogoutHandler(OAuth2AuthorizedClientService clientService) {
        this.clientService = clientService;
        this.restTemplate = new RestTemplate();
    }

    @Override
    public void logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
        // Lógica de logout
        OAuth2AuthenticationToken oauthToken = (OAuth2AuthenticationToken) authentication;

        String endSessionEndpoint = "TU_URL_KEYCLOAK/protocol/openid-connect/logout";

        try {
            // Realizar el logout en Keycloak
            restTemplate.getForEntity(endSessionEndpoint, String.class);

            // Limpiar el token del cliente
            clientService.removeAuthorizedClient(
                    oauthToken.getAuthorizedClientRegistrationId(),
                    oauthToken.getName());

        } catch (Exception e) {
            // Manejar errores
        }

    }
}
