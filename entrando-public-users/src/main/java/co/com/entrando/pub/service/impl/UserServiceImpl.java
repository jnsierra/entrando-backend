package co.com.entrando.pub.service.impl;

import co.com.entrando.datos.entity.User;
import co.com.entrando.datos.entity.UserType;
import co.com.entrando.datos.repository.UserRepository;
import co.com.entrando.dto.UsuarioDto;
import co.com.entrando.exception.BusinessException;
import co.com.entrando.exception.enumeration.TYPE_EXCEPTION;
import co.com.entrando.pub.mapper.UserMapper;
import co.com.entrando.pub.service.UserService;
import co.com.entrando.pub.service.UserTypeService;
import freemarker.template.Configuration;
import freemarker.template.Template;
import jakarta.mail.MessagingException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import co.com.entrando.enumeration.USER_STATE;
import java.io.StringWriter;
import java.time.LocalDate;
import java.util.*;
import java.util.function.Function;
import java.util.function.UnaryOperator;

@Service
@Slf4j
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PasswordServiceImpl passwordService;
    private final EmailServiceImpl emailService;
    private UserTypeService userTypeService;
    private static final String USER_TYPE_NEW = "ROLE_UNVERIFIED_USER";
    private UnaryOperator<UsuarioDto> functionUser = user -> UserMapper.INSTANCE.map(user.getEmail()
            , user.getName()
            , "0"
            , "S"
            , USER_STATE.ACTIVE
            , "000");
    private Function<String, UserType> functionGetUserType = item -> {
        Optional<UserType> obj = userTypeService.findByType(item);
        return obj.orElseThrow(() -> new BusinessException(2L, TYPE_EXCEPTION.ERROR, String.format("Error finding Type User %s", USER_TYPE_NEW)));
    };
    private Function<UsuarioDto, Boolean> functionCreateUser = functionUser.andThen(this::generatePassword)
            .andThen(UserMapper.INSTANCE::map)
            .andThen(this::save)
            .andThen(this::validateInsert)
            .andThen(this::sendNotification);
    @Autowired
    public UserServiceImpl(UserRepository userRepository, PasswordServiceImpl passwordService, EmailServiceImpl emailService, UserTypeService userTypeService) {
        this.userRepository = userRepository;
        this.passwordService = passwordService;
        this.emailService = emailService;
        this.userTypeService = userTypeService;
    }
    @Override
    @Transactional
    public Boolean saveUserPublic(UsuarioDto usuarioDto) {
        return functionCreateUser.apply(usuarioDto);
    }
    private UsuarioDto generatePassword(UsuarioDto usuarioDto){
        usuarioDto.setPassword(passwordService.generatePassayPassword(8));
        return usuarioDto;
    }
    private Optional<User> save(User userEntity){
        userEntity.setCreatedBy("external_user");
        userEntity.setLastModifiedBy("external_user");
        userEntity.setCreatedDate(LocalDate.now());
        userEntity.setLastModifiedDate(LocalDate.now());
        userEntity.setUserTypes(new HashSet<>(Arrays.asList(this.functionGetUserType.apply(USER_TYPE_NEW))));
        User user = userRepository.save(userEntity);
        return Objects.nonNull(user.getId()) ? Optional.of(user): Optional.empty() ;
    }
    private User validateInsert(Optional<User> user){
        if(user.isEmpty()){
            throw new BusinessException(2L, TYPE_EXCEPTION.ERROR, "Error inserting user");
        }
        return user.get();
    }
    private boolean sendNotification(User userEntity){
        try {
            if(!emailService.sendHtmlMessage(userEntity.getEmail(), "Nueva cuenta", this.getTemplate(userEntity.getEmail(), userEntity.getPassword()))){
                throw new BusinessException(2L, TYPE_EXCEPTION.ERROR, "Error send email notification");
            }
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
        return Boolean.TRUE;
    }

    private String getTemplate(String email, String password){
        try {
            Configuration config = new Configuration(Configuration.VERSION_2_3_31);
            config.setClassForTemplateLoading(getClass(), "/");
            Template template = config.getTemplate("bienvenida.ftl");
            Map<String, Object> datos = new HashMap<>();
            datos.put("email", email);
            datos.put("password", password);
            StringWriter sw = new StringWriter();
            template.process(datos, sw);
            return sw.toString();
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return "Ok";
    }
}