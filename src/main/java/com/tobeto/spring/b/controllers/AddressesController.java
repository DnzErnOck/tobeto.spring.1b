package com.tobeto.spring.b.controllers;

import com.tobeto.spring.b.entities.Address;
import com.tobeto.spring.b.repositories.AddressRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/address")
public class AddressesController {
    private final AddressRepository addressRepository;

    public AddressesController(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    public List<Address> listAll(){
        return addressRepository.findAll();
    }
    @GetMapping("{id}")
    public Address getById(@PathVariable int id){
        return addressRepository.findById(id).orElseThrow();
    }
    @PostMapping
    public void add(@RequestBody Address address){
        addressRepository.save(address);
    }
    @PutMapping("{id}")
    public Address update(@RequestBody Address newAddress,@PathVariable int id){
        Optional<Address> address= addressRepository.findById(id);
        if (address.isPresent()){
            Address foundAddress=address.get();
            foundAddress.setPostalCode(newAddress.getPostalCode());
            foundAddress.setAddressDetail(newAddress.getAddressDetail());
            addressRepository.save(foundAddress);
            return foundAddress;
        }
        else {
            return null;
        }
    }
    @DeleteMapping
    public void delete(@PathVariable int id){
        addressRepository.deleteById(id);
    }
}
