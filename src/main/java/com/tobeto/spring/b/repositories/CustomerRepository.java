package com.tobeto.spring.b.repositories;

import com.tobeto.spring.b.entities.Customer;
import com.tobeto.spring.b.services.dtos.responses.customer.FindBetweenAgeAvg;
import com.tobeto.spring.b.services.dtos.responses.customer.GetListCustomerResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CustomerRepository extends JpaRepository<Customer,Integer> {
    List<Customer> findByAgeGreaterThan(int age);
    List<Customer>  findByNameAndSurName(String name, String surName);

    @Query("select new com.tobeto.spring.b.services.dtos.responses.customer.GetListCustomerResponse(c.name,c.surName,c.age) from Customer c where c.age <= :age")
    List<GetListCustomerResponse> findByLessAge(int age);

    @Query("select new com.tobeto.spring.b.services.dtos.responses.customer.GetListCustomerResponse(c.name,c.surName,c.age) from Customer c where c.age between :minAge and :maxAge")
    List<GetListCustomerResponse> findBetweenAge(int minAge, int maxAge);

    //@Query("select AVG(c.age) from Customer c where c.age between :minAge and :maxAge")
    //Double findBetweenAgeAvg2(int minAge, int maxAge);
    @Query("select new com.tobeto.spring.b.services.dtos.responses.customer.FindBetweenAgeAvg(AVG(c.age)) from Customer c where c.age between :minAge and :maxAge")
    FindBetweenAgeAvg findBetweenAgeAvg(int minAge, int maxAge);
}

