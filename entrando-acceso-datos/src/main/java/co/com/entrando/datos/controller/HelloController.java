package co.com.entrando.datos.controller;

import org.springframework.http.ResponseEntity;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
public class HelloController {
    @GetMapping("/api/public")
    public ResponseEntity<String> getPublic() {
        return ResponseEntity.ok("Contenu Public");
    }
    @GetMapping("/api/private")
    public ResponseEntity<String> getPrivate() {
//        // Obtener la autenticación actual
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//
//        // Obtener los roles
//        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
//
//        // Imprimir o verificar roles
//        authorities.forEach(authority -> {
//            System.out.println("Role: " + authority.getAuthority());
//        });
//
//        // Verificar si tiene un rol específico
//        boolean isUser = authentication.getAuthorities().stream()
//                .anyMatch(r -> r.getAuthority().equals("ROLE_user"));
//        System.out.println("Este es el rol: " + isUser);
        return ResponseEntity.ok("Contenu Privé");
    }

}
