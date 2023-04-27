package com.developper.projetindus.controller;


//import com.developper.projetindus.config.JwtGeneratorInterface;
import com.developper.projetindus.dto.AuthRequest;
import com.developper.projetindus.entity.UserEntity;
import com.developper.projetindus.service.UserService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    ///private final JwtGeneratorInterface jwtGenerator;
    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
        //this.jwtGenerator = jwtGenerator;
    }


    @PostMapping
    public UserEntity create(@RequestBody UserEntity userEntity){
        System.out.println("Create new user :"+userEntity.toString());
        return userService.create(userEntity);
    }

    @GetMapping
    public List<UserEntity> getAll(){
        System.out.println("Get all users");
        return userService.getAll();
    }

    @GetMapping("/{id}")
    public UserEntity getById(@PathVariable Long id) {
        System.out.println("Get user by ID: " + id);
        return userService.readOne(id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        System.out.println("Delete user with id : "+id);
        try {
            userService.delete(id);
            return ResponseEntity.noContent().build();
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public UserEntity update(@PathVariable Long id, @RequestBody UserEntity userEntity) {
        System.out.println("update user by ID: " + id);
        return userService.update(id,userEntity);
    }



}
