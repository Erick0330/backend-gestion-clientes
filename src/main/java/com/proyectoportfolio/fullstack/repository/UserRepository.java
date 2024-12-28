package com.proyectoportfolio.fullstack.repository;

import com.proyectoportfolio.fullstack.entity.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Long> {

    User findByUserName(String username);

    boolean existsByUserName(String userName);
}
