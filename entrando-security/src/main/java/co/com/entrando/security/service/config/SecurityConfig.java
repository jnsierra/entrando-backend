package co.com.entrando.security.service.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.core.convert.converter.Converter;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import java.util.stream.Collectors;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain resourceServerFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(auth -> auth
                // Allows preflight requests from browser
                .requestMatchers(new AntPathRequestMatcher("/api/public*"))
                .permitAll()
                .requestMatchers(new AntPathRequestMatcher("/customers*", HttpMethod.OPTIONS.name()))
                .permitAll()
                .requestMatchers(new AntPathRequestMatcher("/customers*"))
                .hasRole("user")
                .requestMatchers(new AntPathRequestMatcher("/"))
                .permitAll()
                .anyRequest()
                .authenticated());

        http.oauth2ResourceServer(oauth2 -> oauth2
                .jwt(jwt -> jwt
                        .jwtAuthenticationConverter(jwtAuthenticationConverter())
                ));

        return http.build();
    }

    private JwtAuthenticationConverter jwtAuthenticationConverter() {
        JwtAuthenticationConverter jwtConverter = new JwtAuthenticationConverter();

        // Crear el convertidor personalizado para roles de Keycloak
        JwtGrantedAuthoritiesConverter keycloakConverter = new JwtGrantedAuthoritiesConverter();
        keycloakConverter.setAuthorityPrefix("ROLE_");
        keycloakConverter.setAuthoritiesClaimName("roles");

        // Crear un convertidor que combine roles de realm_access
        Converter<Jwt, Collection<GrantedAuthority>> customConverter = jwt -> {
            Collection<GrantedAuthority> authorities = new ArrayList<>();

            // Obtener roles del realm_access
            Map<String, Object> realmAccess = jwt.getClaim("realm_access");
            if (realmAccess != null && realmAccess.get("roles") != null) {
                Collection<String> roles = (Collection<String>) realmAccess.get("roles");
                authorities.addAll(
                        roles.stream()
                                .map(role -> new SimpleGrantedAuthority("ROLE_" + role))
                                .collect(Collectors.toList())
                );
            }

            return authorities;
        };

        jwtConverter.setJwtGrantedAuthoritiesConverter(customConverter);
        return jwtConverter;
    }
}