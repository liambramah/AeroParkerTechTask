package com.aeroparker.aeroparkertechtask.model.repositories;

import com.aeroparker.aeroparkertechtask.model.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {
    Optional<Customer> findCustomerByEmailAddress(String emailAddress);
}