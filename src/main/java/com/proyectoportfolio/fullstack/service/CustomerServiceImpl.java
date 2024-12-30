package com.proyectoportfolio.fullstack.service;

import com.proyectoportfolio.fullstack.DTO.CustomerDTO;
import com.proyectoportfolio.fullstack.entity.Customer;
import com.proyectoportfolio.fullstack.entity.Image;
import com.proyectoportfolio.fullstack.exception.ResourceNotFoundException;
import com.proyectoportfolio.fullstack.repository.CustomerRepository;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;


@Service
public class CustomerServiceImpl implements CustomerService{

    private final CustomerRepository customerRepository;
    private final ImageService imageService;

    public CustomerServiceImpl(CustomerRepository customerRepository, ImageService imageService) {
        this.customerRepository = customerRepository;
        this.imageService = imageService;
    }

    @Override
    public CustomerDTO save(CustomerDTO customer, MultipartFile file) throws IOException {


        try{
            ModelMapper modelMapper = new ModelMapper();
            Customer customer1 = modelMapper.map(customer,
                    Customer.class);
            if(file != null && !file.isEmpty()){
                Image image = imageService.uploadImage(file);
                customer1.setImage(image);
            }

            customerRepository.save(customer1);

            return customer;

        }catch (Exception e){
            throw new UnsupportedOperationException("Error al guardar" +
                    "el cliente");
        }
    }

    @Override
    public List<CustomerDTO> findAll() {

        ModelMapper modelMapper = new ModelMapper();

        return customerRepository.findAll().stream()
                .map(entity -> modelMapper.map(entity,
                        CustomerDTO.class)).collect(Collectors.toList());
    }

    @Override
    public CustomerDTO findById(Long id) {
        Optional<Customer> customer = customerRepository.findById(id);

        if(customer.isPresent()){
            ModelMapper modelMapper = new ModelMapper();
            Customer currentCustomer = customer.get();
            return modelMapper.map(currentCustomer,
                    CustomerDTO.class);
        }
        else {
            return new CustomerDTO();
        }
    }

    public void delete(Long id) throws IOException {
        Optional<Customer> customer1 = customerRepository.findById(id);

        if (customer1.isPresent()) {

            Customer currentCustomer = customer1.get();
            if (currentCustomer.getImage() != null) {
                imageService.deleteImage(currentCustomer.getImage());
            }
            customerRepository.deleteById(currentCustomer.getId());
        }
    }

    @Override
    public CustomerDTO update(CustomerDTO customer, Long id) {
        Optional<Customer> customer1 = customerRepository.findById(id);

        if(customer1.isPresent()){
            Customer currentCustomer = customer1.get();
            currentCustomer.setFirstName(customer.getFirstName());
            currentCustomer.setLastName(customer.getLastName());
            currentCustomer.setEmail(customer.getEmail());


            customerRepository.save(currentCustomer);
            ModelMapper modelMapper = new ModelMapper();

            return modelMapper.map(currentCustomer, CustomerDTO.class);
        }
        else {
            throw new IllegalArgumentException("El usuario no existe");
        }

    }

    @Override
    @Transactional
    public CustomerDTO updateCustomerImage(MultipartFile file, CustomerDTO customer, Long id) throws IOException {
        Optional<Customer> customer1 = customerRepository.findById(id);

        if (customer1.isPresent()) {
            Customer currentCustomer = customer1.get();
            if (currentCustomer.getImage() != null) {
                System.out.println("Es distinto de null");
                imageService.deleteImage(currentCustomer.getImage());
            }

            Image newImage = imageService.uploadImage(file);
            currentCustomer.setImage(newImage);

            customerRepository.save(currentCustomer);

            ModelMapper modelMapper = new ModelMapper();

            return modelMapper.map(currentCustomer, CustomerDTO.class);
        } else {
            throw new IllegalArgumentException("El usuario no existe");
        }
    }
}
