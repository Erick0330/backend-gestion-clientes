package com.proyectoportfolio.fullstack.DTO;


import com.proyectoportfolio.fullstack.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SignupRequest {

    private String userName;
    private String password;
    private Role role;

}
