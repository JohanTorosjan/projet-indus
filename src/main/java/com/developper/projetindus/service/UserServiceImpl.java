package com.developper.projetindus.service;

import com.developper.projetindus.dto.FriendsDTO;
import com.developper.projetindus.dto.UpdateUserDTO;
import com.developper.projetindus.dto.UserCreateDTO;
import com.developper.projetindus.entity.CategoryEntity;
import com.developper.projetindus.entity.UserEntity;
import com.developper.projetindus.repository.CategoryRepository;
import com.developper.projetindus.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService/*, UserDetailsService*/ {


    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository,CategoryRepository categoryRepository) {
        this.userRepository = userRepository;
        this.categoryRepository = categoryRepository;
    }



    @Transactional
    @Override
    public UserEntity create(UserCreateDTO userCreateDTO) {
        UserEntity userEntity = new UserEntity();
        userEntity.setAnswered_questions(0);
        userEntity.setConfirmed_account(false);
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        Date dob = null;
        try {
            dob = format.parse(userCreateDTO.getDob());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        userEntity.setDob(dob);
        userEntity.setFirebase_id(userCreateDTO.getFirebase_id());
        userEntity.setName(userCreateDTO.getName());

        userEntity.setEmail(userCreateDTO.getEmail());
        userEntity.setHas_active_session(false);
        userEntity.setInstagram(userCreateDTO.getInstagram());



        UserEntity user = userRepository.save(userEntity);
        List<CategoryEntity> allCategories = categoryRepository.findAll();
        for(CategoryEntity category: allCategories){
            userRepository.createAssociation(user.getId(),category.getId());
        }
        return user;
    }


    public String confirmAccount(Long id){
        Optional<UserEntity> userOptional = userRepository.findById(id);
        if (userOptional.isPresent()) {
            UserEntity user = userOptional.get();
            user.setConfirmed_account(true);

            userRepository.save(user);
            return "success";


        }
        else {
            throw new EntityNotFoundException("User with id" +id+ " not found");
        }
    }

    @Override
    public UserEntity update(Long id, UpdateUserDTO userEntity) {
        Optional<UserEntity> userOptional = userRepository.findById(id);
        if (userOptional.isPresent()) {

            UserEntity user = userOptional.get();
            user.setName(userEntity.getName());
            user.setSurname(userEntity.getName());
            user.setInstagram(userEntity.getInstagram());
            user.setEmail(userEntity.getEmail());
           // user.setPassword(userEntity.getPassword());
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
            Date dob = null;
            try {
                dob = format.parse(userEntity.getDob());
            } catch (ParseException e) {
                e.printStackTrace();
            }
            user.setDob(dob);
            //user.setHas_active_session(userEntity.isHasActiveSession());

            return userRepository.save(user);

        } else {
            throw new EntityNotFoundException("User with id" +id+ " not found");
        }
    }

    @Override
    public List<UserEntity> getAll() {
        return userRepository.findAll();
    }

    @Override
    public UserEntity readOne(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("User with id " + id + " not found"));
    }

    @Override
    public UserEntity getByFirebaseId(String id) {
        return userRepository.findByFirebase_Id(String.valueOf(id));//.orElseThrow(() -> new EntityNotFoundException("User with firebase xid " + id + " not found"));
    }

    @Override
    public void delete(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public List<FriendsDTO> getFriends(Long id) {
        List<UserEntity> friends = userRepository.getFriends(id);

        List<FriendsDTO> friendsDTOS = new ArrayList<>();
       // if(friends.isEmpty()){throw new EntityNotFoundException("NO FRIENDS");}
      //  else{
          for(UserEntity users:friends){
              FriendsDTO friend = new FriendsDTO(users.getId(),users.getName(),users.getInstagram(),users.getDob());
              friendsDTOS.add(friend);
        }
          return friendsDTOS;
     //   }
    }


    /*
    @Override
    public UserEntity findByEmailAndPassword(String email,String password) throws UsernameNotFoundException {
        UserEntity user = userRepository.findByEmailAndPassword(email,password);
        if(user == null){
            throw new UsernameNotFoundException("Invalid email or password");
        }
        return user;
    }


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UserEntity user = userRepository.findByEmail(email).orElseThrow(() ->  new UsernameNotFoundException("User Not Found with email: " + email));
        System.out.println(user.getName());
        return User.builder().username(user.getName()).password(user.getPassword()).build();
    }
    */

}
