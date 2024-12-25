package com.proyectoportfolio.fullstack.repository;

import com.proyectoportfolio.fullstack.entity.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {

    User findByUserName(String username);
}
