package com.tobeto.spring.b.repositories;

import com.tobeto.spring.b.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer,Integer> {
}
