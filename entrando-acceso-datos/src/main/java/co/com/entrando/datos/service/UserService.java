package co.com.entrando.datos.service;


import co.com.entrando.datos.entity.User;

public interface UserService {
    User save(User userEntity);
    boolean updatePassword(String email, String password);
    boolean deleteUserType(String email);
    boolean addUserType(String email,String userType);
}
