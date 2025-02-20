package co.com.entrando.datos.controller;

import co.com.entrando.datos.entity.User;
import co.com.entrando.datos.mapper.UserMapper;
import co.com.entrando.datos.service.UserService;
import co.com.entrando.dto.UsuarioDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v.1/user")
public class UserController {
    private final UserService userService;
    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }
    @PostMapping(value = "/", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE )
    public ResponseEntity<UsuarioDto> save(@RequestBody UsuarioDto usuarioDto){
        User entity = UserMapper.INSTANCE.map(usuarioDto);
        return ResponseEntity.ok(UserMapper.INSTANCE.map( userService.save(entity) ));
    }
    @PostMapping(value = "/changePassword")
    public ResponseEntity<Boolean> updatePasswordUser(@RequestBody UsuarioDto usuarioDto){
        return ResponseEntity.ok(userService.updatePassword(usuarioDto.getEmail(), usuarioDto.getPassword()));
    }
    @DeleteMapping(value = "/UserType/by/{email}")
    public ResponseEntity<Boolean> deleteUserTypeByEmail(@PathVariable("email")String email){
        return ResponseEntity.ok(userService.deleteUserType(email));
    }
    @PostMapping(value = "/userTypes/{user_type}")
    public ResponseEntity<Boolean> addUserTypeByEmail(@RequestBody UsuarioDto usuarioDto
    , @PathVariable("user_type")String userType){
        return ResponseEntity.ok(userService.addUserType(usuarioDto.getEmail(), userType));
    }
}