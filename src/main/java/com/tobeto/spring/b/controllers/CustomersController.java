package com.tobeto.spring.b.controllers;

import com.tobeto.spring.b.dtos.requests.car.UpdateCarRequest;
import com.tobeto.spring.b.dtos.requests.customer.AddCustomerRequest;
import com.tobeto.spring.b.dtos.requests.customer.UpdateCustomerRequest;
import com.tobeto.spring.b.dtos.responses.car.GetCarListResponse;
import com.tobeto.spring.b.dtos.responses.customer.GetCustomerResponse;
import com.tobeto.spring.b.dtos.responses.customer.GetListCustomerResponse;
import com.tobeto.spring.b.entities.Brand;
import com.tobeto.spring.b.entities.Car;
import com.tobeto.spring.b.entities.Customer;
import com.tobeto.spring.b.repositories.CustomerRepository;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
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
    public List<GetListCustomerResponse> getAll(){
        List<Customer> customerList= customerRepository.findAll();
        List<GetListCustomerResponse> getListCustomerResponses=new ArrayList<>();
        for (Customer customer: customerList)
        {
            GetListCustomerResponse response= new GetListCustomerResponse();
            response.setName(customer.getName());
            response.setSurName(customer.getSurName());
            getListCustomerResponses.add(response);
        }
        return getListCustomerResponses;
    }
    @GetMapping("{id}")
    public GetCustomerResponse getById(@PathVariable int id){
        Customer customer= customerRepository.findById(id).orElseThrow();
        GetCustomerResponse getCustomerResponse = new GetCustomerResponse();
        getCustomerResponse.setName(customer.getName());
        getCustomerResponse.setSurName(customer.getSurName());
        return getCustomerResponse;
    }
    @PostMapping
    public void add(@RequestBody AddCustomerRequest addCustomerRequest){
        Customer customer=new Customer();
        customer.setName(addCustomerRequest.getName());
        customer.setSurName(addCustomerRequest.getSurName());
        customerRepository.save(customer);
    }
    @PutMapping("{id}")
    public void update(@RequestBody UpdateCustomerRequest updateCustomerRequest, @PathVariable int id){
        Optional<Customer> customer= customerRepository.findById(id);
        if (customer.isPresent()){
            Customer foundCustomer=customer.get();
            foundCustomer.setName(updateCustomerRequest.getName());
            foundCustomer.setSurName(updateCustomerRequest.getSurName());
            customerRepository.save(foundCustomer);
        }

    }
    @DeleteMapping("{id}")
    public void delete(@PathVariable int id){
        customerRepository.deleteById(id);
    }
}
