package com.tobeto.spring.b.repositories;

import com.tobeto.spring.b.entities.Address;
import com.tobeto.spring.b.services.dtos.responses.address.GetAddressListResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AddressRepository extends JpaRepository<Address,Integer> {
    List<Address> findByAddressDetailContaining(String addressDetail);
    List<Address> findByAddressDetailIsNull();

    @Query("select new com.tobeto.spring.b.services.dtos.responses.address.GetAddressListResponse"
            +"(a.postalCode,a.addressDetail"
            +",new com.tobeto.spring.b.services.dtos.responses.city.GetCityListResponse(c.name)"
            + ") from Address a inner join a.city c where a.postalCode is null")
    List<GetAddressListResponse> findPostalCodeIsNull();

    @Query("select new com.tobeto.spring.b.services.dtos.responses.address.GetAddressListResponse"
            +"(a.postalCode,a.addressDetail"
            +",new com.tobeto.spring.b.services.dtos.responses.city.GetCityListResponse(c.name)"
            + ") from Address a inner join a.city c where c.name is null")
    List<GetAddressListResponse> findCityNameIsNull();
}
