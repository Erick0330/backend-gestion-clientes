package com.proyectoportfolio.fullstack.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.proyectoportfolio.fullstack.DTO.SignupRequest;
import com.proyectoportfolio.fullstack.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/signup")
@CrossOrigin(origins = "http://localhost:4200")
public class SignupController {

    private final AuthService authService;

    public SignupController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping
    public ResponseEntity<String> signupCustomer(@RequestBody SignupRequest signupRequest) throws JsonProcessingException {
        boolean isUserCreated = authService.createCustomer(signupRequest);

        if(!isUserCreated){
            Map<String, String> response = new HashMap<>();
            response.put("message", "User created successfully");

            ObjectMapper mapper = new ObjectMapper(); String jsonResponse = mapper.writeValueAsString(response);

            return ResponseEntity.status(HttpStatus.CREATED).body(jsonResponse);
        }
        else
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Failed to create user");

    }
}
