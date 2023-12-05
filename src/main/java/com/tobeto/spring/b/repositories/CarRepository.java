package com.tobeto.spring.b.repositories;

import com.tobeto.spring.b.entities.Car;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarRepository extends JpaRepository<Car, Integer> {

}
