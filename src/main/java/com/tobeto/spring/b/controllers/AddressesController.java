package com.tobeto.spring.b.controllers;

import com.tobeto.spring.b.dtos.requests.address.AddAddressRequest;
import com.tobeto.spring.b.dtos.responses.address.GetAddressListResponse;
import com.tobeto.spring.b.dtos.responses.address.GetAddressResponse;
import com.tobeto.spring.b.dtos.responses.car.GetCarListResponse;
import com.tobeto.spring.b.dtos.responses.car.GetCarResponse;
import com.tobeto.spring.b.entities.Address;
import com.tobeto.spring.b.entities.Car;
import com.tobeto.spring.b.repositories.AddressRepository;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/address")
public class AddressesController {
    private final AddressRepository addressRepository;

    public AddressesController(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    public List<GetAddressListResponse> getAll(){
        List<Address> addressList= addressRepository.findAll();
        List<GetAddressListResponse> getAddressListResponseList=new ArrayList<>();
        for (Address address: addressList) {
            GetAddressListResponse response = new GetAddressListResponse();
            response.setPostalCode(address.getPostalCode());
            response.setAddressDetail(address.getAddressDetail());
            getAddressListResponseList.add(response);
        }
        return getAddressListResponseList;
    }
    @GetMapping("{id}")
    public GetAddressResponse getById(@PathVariable int id){
        Address address = addressRepository.findById(id).orElseThrow();
        GetAddressResponse getAddressResponse =new GetAddressResponse();
        getAddressResponse.setPostalCode(address.getPostalCode());
        getAddressResponse.setAddressDetail(address.getAddressDetail());
        return getAddressResponse;
    }
    @PostMapping
    public void add(@RequestBody GetAddressResponse addressResponse){
        Address address = new Address();
        address.setPostalCode(addressResponse.getPostalCode());
        address.setAddressDetail(addressResponse.getAddressDetail());
        addressRepository.save(address);
    }
    @PutMapping("{id}")
    public void update(@RequestBody AddAddressRequest addAddressRequest, @PathVariable int id){
        Optional<Address> address= addressRepository.findById(id);
        if (address.isPresent()){
            Address foundAddress=address.get();
            foundAddress.setPostalCode(addAddressRequest.getPostalCode());
            foundAddress.setAddressDetail(addAddressRequest.getAddressDetail());
            addressRepository.save(foundAddress);
        }

    }
    @DeleteMapping
    public void delete(@PathVariable int id){
        addressRepository.deleteById(id);
    }
}
