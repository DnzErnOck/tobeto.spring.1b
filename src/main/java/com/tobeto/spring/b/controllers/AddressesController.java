package com.tobeto.spring.b.controllers;

import com.tobeto.spring.b.services.abstracts.AddressService;
import com.tobeto.spring.b.services.dtos.requests.address.AddAddressRequest;
import com.tobeto.spring.b.services.dtos.requests.address.UpdateAddressRequest;
import com.tobeto.spring.b.services.dtos.responses.address.GetAddressListResponse;
import com.tobeto.spring.b.services.dtos.responses.address.GetAddressResponse;
import com.tobeto.spring.b.entities.Address;
import com.tobeto.spring.b.repositories.AddressRepository;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/address")
@AllArgsConstructor
public class AddressesController {
    private final AddressService addressService;

    @GetMapping
    public List<GetAddressListResponse> getAll(){
        return addressService.getAll();
    }
    @GetMapping("{id}")
    public GetAddressResponse getById(@PathVariable int id){
        return addressService.getById(id);
    }
    @PostMapping
    public void add(@RequestBody AddAddressRequest addAddressRequest){
        addressService.add(addAddressRequest);
    }
    @PutMapping("{id}")
    public void update(@RequestBody UpdateAddressRequest updateAddressRequest, @PathVariable int id){
        addressService.update(updateAddressRequest,id);

    }
    @DeleteMapping
    public void delete(@PathVariable int id){
        addressService.delete(id);
    }

    @GetMapping("getAddressDetails")
    public List<GetAddressListResponse> containAdresssDetails(@RequestParam String addressDetail){
        return addressService.containAdresssDetails(addressDetail);
    }
    @GetMapping("nullAddressDetails")
    public List<GetAddressListResponse> nullAdressDetails(){
        return addressService.nullAdressDetails();
    }
}
