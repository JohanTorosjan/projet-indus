package com.developper.projetindus.service;

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
    public UserEntity create(UserEntity userEntity) {
        UserEntity user = userRepository.save(userEntity);
        List<CategoryEntity> allCategories = categoryRepository.findAll();
        for(CategoryEntity category: allCategories){
            userRepository.createAssociation(user.getId(),category.getId());
        }
        return user;
    }



    @Override
    public UserEntity update(Long id, UserEntity userEntity) {
        Optional<UserEntity> userOptional = userRepository.findById(id);
        if (userOptional.isPresent()) {

            UserEntity user = userOptional.get();
            user.setName(userEntity.getName());
            user.setSurname(userEntity.getSurname());
            user.setInstagram(userEntity.getInstagram());
            user.setEmail(userEntity.getEmail());
           // user.setPassword(userEntity.getPassword());
            user.setConfirmed_account(userEntity.getConfirmed_account());
            user.setDob(userEntity.getDob());
            user.setDoc(userEntity.getDoc());
            user.setAnswered_questions(userEntity.getAnswered_questions());
            user.setHas_active_session(userEntity.getHas_active_session());

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
    public void delete(Long id) {
        userRepository.deleteById(id);
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
