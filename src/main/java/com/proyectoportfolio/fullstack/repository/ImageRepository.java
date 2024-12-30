package com.proyectoportfolio.fullstack.repository;

import com.proyectoportfolio.fullstack.entity.Customer;
import com.proyectoportfolio.fullstack.entity.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImageRepository extends JpaRepository<Image, Long> {
}
