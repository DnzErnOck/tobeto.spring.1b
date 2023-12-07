package com.tobeto.spring.b.repositories;

import com.tobeto.spring.b.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CustomerRepository extends JpaRepository<Customer,Integer> {
    List<Customer> findByAgeGreaterThan(int age);
    List<Customer>  findByNameAndSurName(String name, String surName);
}
