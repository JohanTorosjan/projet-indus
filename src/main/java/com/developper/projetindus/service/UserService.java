package com.developper.projetindus.service;
import com.developper.projetindus.dto.FriendsDTO;
import com.developper.projetindus.dto.UpdateUserDTO;
import com.developper.projetindus.dto.UserCreateDTO;
import com.developper.projetindus.entity.UserEntity;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {
    UserEntity create(UserCreateDTO userCreateDTO);
    UserEntity update(Long id, UpdateUserDTO updateUserDTO);
    List<UserEntity> getAll();
    UserEntity readOne(Long id);
    UserEntity getByFirebaseId(String id);
    void delete(Long id);

    List<FriendsDTO> getFriends(Long id);

    String confirmAccount(Long id);

    // UserEntity findByEmailAndPassword(String email,String password) throws UsernameNotFoundException;
}
