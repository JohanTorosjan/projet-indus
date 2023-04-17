package com.developper.projetindus.service;
import com.developper.projetindus.entity.UserEntity;
import java.util.List;

public interface UserService {
    UserEntity create(UserEntity userEntity);
    UserEntity update(Long id,UserEntity userEntity);
    List<UserEntity> getAll();
    UserEntity readOne(Long id);
    void delete(Long id);
}
