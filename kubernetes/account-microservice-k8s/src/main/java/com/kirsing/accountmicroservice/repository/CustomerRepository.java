package com.kirsing.accountmicroservice.repository;

import com.kirsing.accountmicroservice.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    Optional<Customer> findByMobileNumber(String mobileNumber);
}
