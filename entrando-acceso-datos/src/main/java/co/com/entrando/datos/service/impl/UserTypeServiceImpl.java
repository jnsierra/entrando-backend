package co.com.entrando.datos.service.impl;

import co.com.entrando.datos.entity.UserType;
import co.com.entrando.datos.repository.UserTypeRepository;
import co.com.entrando.datos.service.UserTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

@Service
public class UserTypeServiceImpl implements UserTypeService {
    private final UserTypeRepository userTypeRepository;
    @Autowired
    public UserTypeServiceImpl(UserTypeRepository userTypeRepository) {
        this.userTypeRepository = userTypeRepository;
    }
    @Override
    public Optional<UserType> getByType(String type) {
        UserType typeObj = userTypeRepository.findByType(type);
        return Objects.isNull(typeObj) ? Optional.empty() : Optional.of(typeObj);
    }
}
