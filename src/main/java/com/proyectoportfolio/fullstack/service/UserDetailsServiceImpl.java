package com.proyectoportfolio.fullstack.service;

import com.proyectoportfolio.fullstack.entity.SecurityUser;
import com.proyectoportfolio.fullstack.entity.User;
import com.proyectoportfolio.fullstack.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUserName(username);

        if(user == null){
            throw new UsernameNotFoundException("User not found with username: "+ username);
        }
        return new SecurityUser(user);
    }
}
