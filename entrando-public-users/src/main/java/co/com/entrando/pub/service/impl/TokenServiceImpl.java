package co.com.entrando.pub.service.impl;

import co.com.entrando.datos.entity.User;
import co.com.entrando.datos.repository.UserRepository;
import co.com.entrando.dto.TokenDto;
import co.com.entrando.pub.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TokenServiceImpl implements TokenService {
    private final UserRepository userRepository;
    public final Integer seconds = Integer.valueOf("3600");
    private static final String SECRET = "Aqt&fNpb9^0i*Xs!94v2v3Ijrp5T0vVQy6wussLv$Bw9$p%9rpW0Yb9rI&5R0yDRI8J25lt^*iSoK@1b8vSz3dy1r5sx#GLFC$tq";

    @Autowired
    public TokenServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    @Transactional
    public Optional<TokenDto> generateTokenUser(String email) {
        Optional<User> user = userRepository.findByEmail(email);
        if (user.isPresent()) {
            String jwt = "";
            Instant now = Instant.now();

            return Optional.of(TokenDto.builder()
                    .token("123")
                    .mensaje("Autenticacion exitosa")
                    .time(seconds)
                    .build());
        }
        return Optional.empty();
    }
    /*
    private List<GrantedAuthority> getRolesByUser(User user) {
        Optional<String> roleStr = user.getUserTypes().stream().map(item -> item.getType())
                .reduce((accumulator, type) -> accumulator.concat(",".concat(type)));
        List<GrantedAuthority> grantedAuthorities;
        if (roleStr.isPresent()) {
            grantedAuthorities = AuthorityUtils.commaSeparatedStringToAuthorityList(roleStr.get());
        } else {
            grantedAuthorities = new ArrayList<>();
        }
        return grantedAuthorities;
    }
    */
}
