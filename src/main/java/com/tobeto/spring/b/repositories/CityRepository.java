package com.tobeto.spring.b.repositories;

import com.tobeto.spring.b.entities.City;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CityRepository extends JpaRepository<City,Integer> {
}
