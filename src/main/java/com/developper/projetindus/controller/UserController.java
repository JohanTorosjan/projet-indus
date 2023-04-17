package com.developper.projetindus.controller;


import com.developper.projetindus.entity.UserEntity;
import com.developper.projetindus.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/User")
public class UserController {

    private final UserService userService;

    @PostMapping
    public UserEntity create(@RequestBody UserEntity userEntity){
        System.out.println("ici");
        System.out.println(userEntity.toString());
        return userService.create(userEntity);
    }

    @GetMapping
    public List<UserEntity> getAll(){
        System.out.println("Get all users");
        return userService.getAll();
    }
    public UserController(UserService userService) {
        this.userService = userService;
    }

}
