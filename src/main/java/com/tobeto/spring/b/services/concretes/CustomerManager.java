package com.tobeto.spring.b.services.concretes;

import com.tobeto.spring.b.entities.Customer;
import com.tobeto.spring.b.repositories.CustomerRepository;
import com.tobeto.spring.b.services.abstracts.CustomerService;
import com.tobeto.spring.b.services.dtos.requests.address.UpdateAddressRequest;
import com.tobeto.spring.b.services.dtos.requests.customer.AddCustomerRequest;
import com.tobeto.spring.b.services.dtos.requests.customer.UpdateCustomerRequest;
import com.tobeto.spring.b.services.dtos.responses.customer.GetCustomerResponse;
import com.tobeto.spring.b.services.dtos.responses.customer.GetListCustomerResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CustomerManager implements CustomerService {
    private final CustomerRepository customerRepository;

    @Override
    public List<GetListCustomerResponse> getAll() {
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

    @Override
    public GetCustomerResponse getById(int id) {
        Customer customer= customerRepository.findById(id).orElseThrow();
        GetCustomerResponse getCustomerResponse = new GetCustomerResponse();
        getCustomerResponse.setName(customer.getName());
        getCustomerResponse.setSurName(customer.getSurName());
        return getCustomerResponse;
    }

    @Override
    public void add(AddCustomerRequest addCustomerRequest) {
        Customer customer=new Customer();
        customer.setName(addCustomerRequest.getName());
        customer.setSurName(addCustomerRequest.getSurName());
        customerRepository.save(customer);
    }

    @Override
    public void update(UpdateCustomerRequest updateCustomerRequest, int id) {
        Optional<Customer> customer= customerRepository.findById(id);
        if (customer.isPresent()){
            Customer foundCustomer=customer.get();
            foundCustomer.setName(updateCustomerRequest.getName());
            foundCustomer.setSurName(updateCustomerRequest.getSurName());
            customerRepository.save(foundCustomer);
        }
    }

    @Override
    public void delete(int id) {
        customerRepository.deleteById(id);
    }

    @Override
    public List<GetListCustomerResponse> getGreaterAger(int age) {
        List<Customer> customerList=customerRepository.findByAgeGreaterThan(age);
        List<GetListCustomerResponse> getListCustomerResponses=new ArrayList<>();
        for (Customer customer :customerList) {
            GetListCustomerResponse response= new GetListCustomerResponse();
            response.setName(customer.getName());
            response.setSurName(customer.getSurName());
            response.setAge(customer.getAge());
            getListCustomerResponses.add(response);
        }
        return getListCustomerResponses;
    }

    @Override
    public List<GetListCustomerResponse> getNameAndSurname(String name, String surName) {
        List<Customer> customerList=customerRepository.findByNameAndSurName(name,surName);
        List<GetListCustomerResponse> getListCustomerResponses=new ArrayList<>();
        for (Customer customer : customerList) {
            GetListCustomerResponse response= new GetListCustomerResponse();
            response.setName(customer.getName());
            response.setSurName(customer.getSurName());
            response.setAge(customer.getAge());
            getListCustomerResponses.add(response);
        }
        return getListCustomerResponses;
    }
}
