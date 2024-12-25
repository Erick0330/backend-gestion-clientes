package com.proyectoportfolio.fullstack.controller;

import com.proyectoportfolio.fullstack.DTO.CustomerDTO;
import com.proyectoportfolio.fullstack.configuration.ExternalizedConfigurations;
import com.proyectoportfolio.fullstack.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;


//http://localhost:8080/api/customers
@RestController
@RequestMapping("/api/customers")
//@CrossOrigin(origins = "http://localhost:4200")
public class CustomerController {
    
    private final CustomerService customerService;

    @Autowired
    private ExternalizedConfigurations externalizedConfigurations;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    //http://localhost:8080/api/customers
    @GetMapping
    public ResponseEntity<List<CustomerDTO>> findAll(){

        System.out.println(externalizedConfigurations.toString());

        return ResponseEntity.ok(customerService.findAll());
    }

    //http://localhost:8080/api/customers/1
    @GetMapping("/{id}")
    public CustomerDTO findById(@PathVariable("id") Long id){
        return customerService.findById(id);
    }

    //http://localhost:8080/api/customers
    @PostMapping("/admin")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<?> save(@RequestPart CustomerDTO customer,
                         @RequestPart("file")MultipartFile file) throws IOException {

        CustomerDTO c = customerService.save(customer, file);
        if(c != null){
            return ResponseEntity.ok(c);
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encontró el cliente con id: "+ customer.getId());
    }

    //http://localhost:8080/api/customers/1
    @DeleteMapping("/admin/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<?> deleteById(@PathVariable("id") Long id) throws IOException {

        try{
            customerService.delete(id);
            return ResponseEntity.noContent().build();
        } catch (IllegalArgumentException e){

            System.out.println(e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se eliminó debido a que no se " +
                    "encontró el cliente con id: "+ id);
        }



    }

    //http://localhost:8080/api/customers
    @PutMapping("/admin/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<?> updateCustomer(@RequestBody CustomerDTO customer, @PathVariable("id")Long id){

        CustomerDTO customerDB = customerService.findById(id);

        if(customerDB != null){
            customerService.update(customer, id);

            return ResponseEntity.noContent().build();
        }


        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se actualizó debido a que no se " +
                "encontró el cliente con id: "+ customer.getId());
    }

    @PutMapping("/admin/{id}/image")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<?> updateCustomerImage(@PathVariable("id") Long id, @RequestPart("file")
                                                        MultipartFile file) throws IOException {
        CustomerDTO customer = customerService.findById(id);
        if(customer != null){
            customerService.updateCustomerImage(file, customer, id);

            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se actualizó la imagen debido a que no se " +
                "encontró el cliente con id: "+ id);
    }

}
