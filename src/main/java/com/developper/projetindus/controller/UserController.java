package com.developper.projetindus.controller;


//import com.developper.projetindus.config.JwtGeneratorInterface;
import com.developper.projetindus.dto.AuthRequest;
import com.developper.projetindus.dto.FriendsDTO;
import com.developper.projetindus.dto.UpdateUserDTO;
import com.developper.projetindus.dto.UserCreateDTO;
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
    public UserEntity create(@RequestBody UserCreateDTO userCreateDTO){
        System.out.println(userCreateDTO.getFirebase_id());
        System.out.println("Create new user :"+userCreateDTO.toString());
        return userService.create(userCreateDTO);
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


    @GetMapping("firebase_id/{id}")
    public UserEntity getByFirebaseId(@PathVariable String id) {
        System.out.println("Get user by FirebaseID: " + id);
        return userService.getByFirebaseId(id);
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
    public UserEntity update(@PathVariable Long id, @RequestBody UpdateUserDTO updateUserDTO) {
        System.out.println("update user by ID: " + id);
        return userService.update(id,updateUserDTO);
    }

    @GetMapping("friends/{id}")
    public List<FriendsDTO> getFriends(@PathVariable Long id){
        System.out.println("get friend of :"+id);
        return userService.getFriends(id);
    }

    @PutMapping("confirmedAccount/{id}")
    public String confirmAccount(@PathVariable Long id){
        return userService.confirmAccount(id);
    }


}
