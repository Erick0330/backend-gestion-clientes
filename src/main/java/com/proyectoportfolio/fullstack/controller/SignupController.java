package com.proyectoportfolio.fullstack.controller;

import com.proyectoportfolio.fullstack.DTO.SignupRequest;
import com.proyectoportfolio.fullstack.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/signup")
public class SignupController {

    private final AuthService authService;

    public SignupController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping
    public ResponseEntity<String> signupCustomer(@RequestBody SignupRequest signupRequest){
        boolean isUserCreated = authService.createCustomer(signupRequest);

        if(!isUserCreated){
            return ResponseEntity.status(HttpStatus.CREATED).body("User created succesufully");
        }
        else
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Failed to create user");

    }
}
