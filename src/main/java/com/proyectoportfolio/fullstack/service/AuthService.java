package com.proyectoportfolio.fullstack.service;

import com.proyectoportfolio.fullstack.DTO.CustomerDTO;
import com.proyectoportfolio.fullstack.DTO.SignupRequest;
import com.proyectoportfolio.fullstack.entity.Customer;

public interface AuthService {

    boolean createCustomer(SignupRequest signupRequest);
}
