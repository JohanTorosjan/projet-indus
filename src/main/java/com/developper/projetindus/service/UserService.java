package com.developper.projetindus.service;
import com.developper.projetindus.entity.UserEntity;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {
    UserEntity create(UserEntity userEntity);
    UserEntity update(Long id,UserEntity userEntity);
    List<UserEntity> getAll();
    UserEntity readOne(Long id);
    void delete(Long id);

   // UserEntity findByEmailAndPassword(String email,String password) throws UsernameNotFoundException;
}
