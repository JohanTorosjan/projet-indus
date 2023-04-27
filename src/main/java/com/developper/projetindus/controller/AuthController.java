/*
package com.developper.projetindus.controller;

//import com.developper.projetindus.config.JwtGeneratorInterface;
import com.developper.projetindus.dto.AuthRequest;
import com.developper.projetindus.entity.UserEntity;
import com.developper.projetindus.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UserService userService;
    private final JwtGeneratorInterface jwtGenerator;

    @Autowired
    public AuthController(UserService userService, JwtGeneratorInterface jwtGenerator) {
        this.userService = userService;
        this.jwtGenerator = jwtGenerator;
    }

    @PostMapping("/login")
    public ResponseEntity<?> createAuth(@RequestBody AuthRequest request) throws Exception {
        System.out.println("ici");
        try {
            if (request.getEmail() == null || request.getPassword() == null) {
                throw new UsernameNotFoundException("email or password is empty");
            }
            UserEntity userData = userService.findByEmailAndPassword(request.getEmail(),request.getPassword());

            if(userData == null){
                throw new UsernameNotFoundException("email or password is wrong");
            }
            return new ResponseEntity<>(jwtGenerator.generateToken(userData), HttpStatus.OK);
        } catch (UsernameNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        }
    }

    @PostMapping("/register")
    public UserEntity create(@RequestBody UserEntity userEntity){
        System.out.println("Create new user :"+userEntity.toString());
        return userService.create(userEntity);
    }


}

 */
