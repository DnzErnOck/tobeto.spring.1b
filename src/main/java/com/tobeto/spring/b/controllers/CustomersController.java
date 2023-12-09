package com.tobeto.spring.b.controllers;

import com.tobeto.spring.b.services.abstracts.CustomerService;
import com.tobeto.spring.b.services.dtos.requests.customer.AddCustomerRequest;
import com.tobeto.spring.b.services.dtos.requests.customer.UpdateCustomerRequest;
import com.tobeto.spring.b.services.dtos.responses.city.GetCityListResponse;
import com.tobeto.spring.b.services.dtos.responses.customer.FindBetweenAgeAvg;
import com.tobeto.spring.b.services.dtos.responses.customer.GetCustomerResponse;
import com.tobeto.spring.b.services.dtos.responses.customer.GetListCustomerResponse;
import com.tobeto.spring.b.entities.Customer;
import com.tobeto.spring.b.repositories.CustomerRepository;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/customer")
@AllArgsConstructor
public class CustomersController {
    public final CustomerService customerService;

    @GetMapping
    public List<GetListCustomerResponse> getAll(){
        return customerService.getAll();
    }
    @GetMapping("{id}")
    public GetCustomerResponse getById(@PathVariable int id){
        return customerService.getById(id);
    }
    @PostMapping
    public void add(@RequestBody AddCustomerRequest addCustomerRequest){
        customerService.add(addCustomerRequest);
    }
    @PutMapping("{id}")
    public void update(@RequestBody UpdateCustomerRequest updateCustomerRequest, @PathVariable int id){
        customerService.update(updateCustomerRequest,id);

    }
    @DeleteMapping("{id}")
    public void delete(@PathVariable int id){
        customerService.delete(id);
    }

    @GetMapping("getAge")
    public List<GetListCustomerResponse> getGreaterAger(@RequestParam int age){
        return customerService.getGreaterAger(age);
    }
    @GetMapping("getNameAndSurname")
    public List<GetListCustomerResponse> getNameAndSurname(@RequestParam String name, @RequestParam String surName){
        return customerService.getNameAndSurname(name,surName);
    }
    @GetMapping("getLessAge")
    public List<GetListCustomerResponse> getLessAge(@RequestParam int age){
        return customerService.getByLessAge(age);
    }
    @GetMapping("getBetweenAge")
    public List<GetListCustomerResponse> getBetweenAge(@RequestParam int minAge,int maxAge){
        return customerService.getBetweenAge(minAge,maxAge);
    }
    @GetMapping("getCustomerAgeAvg")
    public FindBetweenAgeAvg getBetweenAgeAvg(@RequestParam int minAge, int maxAge){
        return customerService.getBetweenAgeAvg(minAge,maxAge);
    }
}
