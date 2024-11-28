package com.proyectoportfolio.fullstack.service;

import com.proyectoportfolio.fullstack.entity.Customer;
import com.proyectoportfolio.fullstack.entity.Image;
import com.proyectoportfolio.fullstack.exception.ResourceNotFoundException;
import com.proyectoportfolio.fullstack.repository.CustomerRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;


@Service
public class CustomerServiceImpl implements CustomerService{

    private final CustomerRepository customerRepository;
    private final ImageService imageService;

    public CustomerServiceImpl(CustomerRepository customerRepository, ImageService imageService) {
        this.customerRepository = customerRepository;
        this.imageService = imageService;
    }

    @Override
    public Customer save(Customer customer, MultipartFile file) throws IOException {

        if(file != null && !file.isEmpty()){
            Image image = imageService.uploadImage(file);
            customer.setImage(image);
        }
        return customerRepository.save(customer);
    }

    @Override
    public List<Customer> findAll() {
        return customerRepository.findAll();
    }

    @Override
    public Customer findById(Integer id) {
        Customer customer = customerRepository.findById(id).orElseThrow(
                () ->{
                    throw new ResourceNotFoundException("Customer con id: "+
                            id+ " no se encuentra");
                }
        );
        return customerRepository.findById(id).get();
    }

    public void delete(Customer customer) throws IOException {
        if(customer.getImage() != null){
            imageService.deleteImage(customer.getImage());
        }
        customerRepository.deleteById(customer.getId());
    }

    @Override
    public Customer update(Customer customer) {
        return customerRepository.save(customer);
    }

    @Override
    public Customer updateCustomerImage(MultipartFile file, Customer customer) throws IOException {
        if(customer.getImage() != null){
            imageService.deleteImage(customer.getImage());
        }
        Image newImage = imageService.uploadImage(file);
        customer.setImage(newImage);
        return customerRepository.save(customer);
    }
}
