package com.aeroparker.aeroparkertechtask.controller;

import com.aeroparker.aeroparkertechtask.model.entities.Customer;
import com.aeroparker.aeroparkertechtask.model.repositories.CustomerRepository;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.time.Instant;

@Controller
public class CustomerController {
    private final CustomerRepository customerRepository;

    public CustomerController(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }


    //shows the registration page
    @GetMapping("/registration")
    public String registrationPage(){
        return "registration";
    }

    //
    @PostMapping("/registercustomer")
    public String registerCustomer(@Valid @ModelAttribute("customerToRegister")Customer customer){
        customer.setRegistered(Instant.now());
        Customer duplicateEmailCheck = customerRepository.findCustomerByEmailAddress(customer.getEmailAddress()).orElse(null);
        if(duplicateEmailCheck == null) {
            customerRepository.save(customer);
            return "registration-success";
        }else{
            return "email-already-registered";
        }
    }
}
