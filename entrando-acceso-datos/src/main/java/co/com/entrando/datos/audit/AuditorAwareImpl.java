package co.com.entrando.datos.audit;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;

import java.util.Optional;
@Slf4j
public class AuditorAwareImpl implements AuditorAware<String> {
    @Override
    public Optional<String> getCurrentAuditor() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated()) {
            return Optional.of("publico");
        }
        if(authentication instanceof JwtAuthenticationToken){
            Jwt jwt = ((JwtAuthenticationToken) authentication).getToken();
            return Optional.ofNullable(jwt.getClaimAsString("preferred_username"));
        }
        return Optional.of("unauthenticated");
    }

}