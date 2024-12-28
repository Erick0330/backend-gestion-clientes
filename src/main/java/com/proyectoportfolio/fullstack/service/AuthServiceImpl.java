package com.proyectoportfolio.fullstack.service;

import com.proyectoportfolio.fullstack.DTO.SignupRequest;
import com.proyectoportfolio.fullstack.entity.User;
import com.proyectoportfolio.fullstack.repository.UserRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService{

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public boolean createCustomer(SignupRequest signupRequest) {
        if(userRepository.existsByUserName(signupRequest.getUserName()))
            return false;

        User user = new User();
        BeanUtils.copyProperties(signupRequest, user);

        String hashPassword = passwordEncoder.encode(signupRequest.getPassword());

        user.setPassword(hashPassword);
        userRepository.save(user);
        return false;
    }
}
