package com.tobeto.spring.b.repositories;

import com.tobeto.spring.b.entities.Address;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AddressRepository extends JpaRepository<Address,Integer> {
    List<Address> findByAddressDetailContaining(String addressDetail);
    List<Address> findByAddressDetailIsNull();
}
