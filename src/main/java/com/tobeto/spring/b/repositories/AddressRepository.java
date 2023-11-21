package com.tobeto.spring.b.repositories;

import com.tobeto.spring.b.entities.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address,Integer> {
}
