package co.com.entrando.datos.service;


import co.com.entrando.datos.entity.UserTicket;

public interface UserService {
    UserTicket save(UserTicket userEntity);
    boolean updatePassword(String email, String password);
    boolean deleteUserType(String email);
    boolean addUserType(String email,String userType);
}
