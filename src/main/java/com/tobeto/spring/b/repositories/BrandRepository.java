package com.tobeto.spring.b.repositories;

import com.tobeto.spring.b.entities.Brand;
import com.tobeto.spring.b.services.dtos.responses.brand.GetBrandListResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

//Generic yapılar
//Generic türler referans type olmak zorundadır
public interface BrandRepository extends JpaRepository<Brand, Integer> {
    // Derived Query Method
    List<Brand> findByNameLikeOrIdEquals(String name,int id);
    //jpa+sql => jpql
    //JPQL => tablo ismi değil entitiy ismi



}
