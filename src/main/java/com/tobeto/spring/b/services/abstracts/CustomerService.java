package com.tobeto.spring.b.services.abstracts;

import com.tobeto.spring.b.services.dtos.requests.address.UpdateAddressRequest;
import com.tobeto.spring.b.services.dtos.requests.customer.AddCustomerRequest;
import com.tobeto.spring.b.services.dtos.requests.customer.UpdateCustomerRequest;
import com.tobeto.spring.b.services.dtos.responses.customer.GetCustomerResponse;
import com.tobeto.spring.b.services.dtos.responses.customer.GetListCustomerResponse;

import java.util.List;

public interface CustomerService {
    List<GetListCustomerResponse> getAll();
    GetCustomerResponse getById(int id);
    void add(AddCustomerRequest addCustomerRequest);
    void update(UpdateCustomerRequest updateCustomerRequest, int id);
    void delete(int id);
    List<GetListCustomerResponse> getGreaterAger(int age);
    List<GetListCustomerResponse> getNameAndSurname(String name,String surName);
}
