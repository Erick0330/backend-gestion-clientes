package com.proyectoportfolio.fullstack.service;

import com.proyectoportfolio.fullstack.entity.Customer;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface CustomerService {

    Customer save(Customer customer, MultipartFile file) throws IOException;
    List<Customer> findAll();
    Customer findById(Integer id);
    void delete(Customer customer) throws IOException;
    Customer update(Customer customer);
    Customer updateCustomerImage(MultipartFile file, Customer customer) throws IOException;
}
