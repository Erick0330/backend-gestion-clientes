package com.proyectoportfolio.fullstack.repository;

import com.proyectoportfolio.fullstack.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository <Customer, Long> {

}
