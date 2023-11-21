package com.tobeto.spring.b.repositories;

import com.tobeto.spring.b.entities.Brand;
import org.springframework.data.jpa.repository.JpaRepository;

//Generic yapılar
//Generic türler referans type olmak zorundadır
public interface BrandRepository extends JpaRepository<Brand, Integer> {

}
