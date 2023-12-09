package com.tobeto.spring.b.services.concretes;

import com.tobeto.spring.b.entities.Address;
import com.tobeto.spring.b.repositories.AddressRepository;
import com.tobeto.spring.b.services.abstracts.AddressService;
import com.tobeto.spring.b.services.dtos.requests.address.AddAddressRequest;
import com.tobeto.spring.b.services.dtos.requests.address.UpdateAddressRequest;
import com.tobeto.spring.b.services.dtos.responses.address.GetAddressListResponse;
import com.tobeto.spring.b.services.dtos.responses.address.GetAddressResponse;
import com.tobeto.spring.b.services.dtos.responses.city.GetCityListResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service()
@AllArgsConstructor
public class AddressManager implements AddressService {
    private final AddressRepository addressRepository;

    @Override
    public List<GetAddressListResponse> getAll() {
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

    @Override
    public GetAddressResponse getById(int id) {
        Address address = addressRepository.findById(id).orElseThrow();
        GetAddressResponse getAddressResponse =new GetAddressResponse();
        getAddressResponse.setPostalCode(address.getPostalCode());
        getAddressResponse.setAddressDetail(address.getAddressDetail());
        return getAddressResponse;
    }

    @Override
    public void add(AddAddressRequest addAddressRequest) {
        int maxAddressDetail = 50;
        if (addAddressRequest.getAddressDetail().length() > maxAddressDetail) {
            throw new RuntimeException("Maksimum karakter sınırını aştınız.Lütfen daha kısa bir ver girişi yapınız.");
        }
        Address address = new Address();
        address.setPostalCode(addAddressRequest.getPostalCode());
        address.setAddressDetail(addAddressRequest.getAddressDetail());
        addressRepository.save(address);
    }

    @Override
    public void update(UpdateAddressRequest updateAddressRequest, int id) {
        Optional<Address> address= addressRepository.findById(id);
        if (address.isPresent()){
            Address foundAddress=address.get();
            foundAddress.setPostalCode(updateAddressRequest.getPostalCode());
            foundAddress.setAddressDetail(updateAddressRequest.getAddressDetail());
            addressRepository.save(foundAddress);
        }
    }

    @Override
    public void delete(int id) {
        addressRepository.deleteById(id);
    }

    @Override
    public List<GetAddressListResponse> containAdresssDetails(String addressDetail) {
        List<Address> addressList=addressRepository.findByAddressDetailContaining(addressDetail);
        List<GetAddressListResponse> getAddressListResponses =new ArrayList<>();
        for (Address address:addressList){
            GetAddressListResponse response = new GetAddressListResponse();
            GetCityListResponse getCityListResponse=new GetCityListResponse(address.getCity().getName());
            response.setAddressDetail(address.getAddressDetail());
            response.setPostalCode(address.getPostalCode());
            response.setCity(getCityListResponse);
            getAddressListResponses.add(response);
        }
        return getAddressListResponses;
    }

    @Override
    public List<GetAddressListResponse> nullAdressDetails() {
        List<Address> addressList=addressRepository.findByAddressDetailIsNull();
        List<GetAddressListResponse> getAddressListResponseList=new ArrayList<>();
        for (Address address:addressList){
            GetAddressListResponse response=new GetAddressListResponse();
            GetCityListResponse getCityListResponse=new GetCityListResponse(address.getCity().getName());
            response.setAddressDetail(address.getAddressDetail());
            response.setPostalCode(address.getPostalCode());
            response.setCity(getCityListResponse);
            getAddressListResponseList.add(response);

        }
            return getAddressListResponseList;

    }

    @Override
    public List<GetAddressListResponse> postalCodeIsNull() {
        return addressRepository.findPostalCodeIsNull();
    }

    @Override
    public List<GetAddressListResponse> cityNameIsNull() {
        return addressRepository.findCityNameIsNull();
    }
}
