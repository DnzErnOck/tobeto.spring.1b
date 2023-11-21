package com.tobeto.spring.b.controllers;

import com.tobeto.spring.b.entities.Brand;
import com.tobeto.spring.b.entities.Customer;
import com.tobeto.spring.b.repositories.CustomerRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/customer")
public class CustomersController {
    public final CustomerRepository customerRepository;

    public CustomersController(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @GetMapping
    public List<Customer> getAll(){
        return customerRepository.findAll();
    }
    @GetMapping("{id}")
    public Customer getById(@PathVariable int id){
        return customerRepository.findById(id).orElseThrow();
    }
    @PostMapping
    public void add(@RequestBody Customer customer){
        customerRepository.save(customer);
    }
    @PutMapping("{id}")
    public Customer update(@RequestBody Customer newCustomer,@PathVariable int id){
        Optional<Customer> customer= customerRepository.findById(id);
        if (customer.isPresent()){
            Customer foundCustomer=customer.get();
            foundCustomer.setId(newCustomer.getId());
            foundCustomer.setName(newCustomer.getName());
            foundCustomer.setSurName(newCustomer.getSurName());
            foundCustomer.setTcNo(newCustomer.getTcNo());
            foundCustomer.setGender(newCustomer.getGender());
            foundCustomer.setPhone(newCustomer.getPhone());
            foundCustomer.setEmail(newCustomer.getEmail());
            foundCustomer.setAge(newCustomer.getAge());
            customerRepository.save(foundCustomer);
            return foundCustomer;
        }
        else {
            return null;
        }
    }
    @DeleteMapping("{id}")
    public void delete(@PathVariable int id){
        customerRepository.deleteById(id);
    }
}
