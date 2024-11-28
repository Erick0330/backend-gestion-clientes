package com.proyectoportfolio.fullstack.controller;

import com.proyectoportfolio.fullstack.configuration.ExternalizedConfigurations;
import com.proyectoportfolio.fullstack.entity.Customer;
import com.proyectoportfolio.fullstack.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

//http://localhost:8080/api/customers
@RestController
@RequestMapping("/api/customers")
@CrossOrigin(origins = "http://localhost:4200")
public class CustomerController {
    
    private final CustomerService customerService;

    @Autowired
    private ExternalizedConfigurations externalizedConfigurations;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    //http://localhost:8080/api/customers
    @GetMapping
    public ResponseEntity<List<Customer>> findAll(){

        System.out.println(externalizedConfigurations.toString());

        return ResponseEntity.ok(customerService.findAll());
    }

    //http://localhost:8080/api/customers/1
    @GetMapping("/{id}")
    public Customer findById(@PathVariable("id") Integer id){
        return customerService.findById(id);
    }

    //http://localhost:8080/api/customers
    @PostMapping
    public ResponseEntity<?> save(@RequestPart Customer customer,
                         @RequestPart("file")MultipartFile file) throws IOException {

        Customer c = customerService.save(customer, file);
        if(c != null){
            return ResponseEntity.ok(c);
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encontró el cliente con id: "+ customer.getId());
    }

    //http://localhost:8080/api/customers/1
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(Customer customer) throws IOException {

        Customer c = customerService.findById(customer.getId());
        if(c != null){
            customerService.delete(c);

            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se eliminó debido a que no se " +
                "encontró el cliente con id: "+ customer.getId());
    }

    //http://localhost:8080/api/customers
    @PutMapping
    public ResponseEntity<?> updateCustomer(@RequestBody Customer customer){

        Customer customerDB = customerService.findById(customer.getId());

        if(customerDB != null){

            customerDB.setFirstName(customer.getFirstName());
            customerDB.setLastName(customer.getLastName());
            customerDB.setEmail(customer.getEmail());

            return ResponseEntity.noContent().build();
        }


        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se actualizó debido a que no se " +
                "encontró el cliente con id: "+ customer.getId());
    }

    @PutMapping("/{id}/image")
    public ResponseEntity<?> updateCustomerImage(@PathVariable Integer id, @RequestPart("file")
                                                        MultipartFile file) throws IOException {
        Customer customer = customerService.findById(id);
        if(customer != null){
            customerService.updateCustomerImage(file, customer);

            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se actualizó la imagen debido a que no se " +
                "encontró el cliente con id: "+ id);
    }

}
