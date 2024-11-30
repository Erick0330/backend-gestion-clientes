package com.proyectoportfolio.fullstack.service;

import com.proyectoportfolio.fullstack.DTO.CustomerDTO;
import com.proyectoportfolio.fullstack.entity.Customer;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public interface CustomerService {

    CustomerDTO save(CustomerDTO customer, MultipartFile file) throws IOException;
    List<CustomerDTO> findAll();
    CustomerDTO findById(Long id);
    void delete(Long id) throws IOException;
    CustomerDTO update(CustomerDTO customer, Long  id);
    CustomerDTO updateCustomerImage(MultipartFile file, CustomerDTO customer, Long id) throws IOException;
}
