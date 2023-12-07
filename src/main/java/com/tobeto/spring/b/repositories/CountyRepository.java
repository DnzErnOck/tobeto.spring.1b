package com.tobeto.spring.b.repositories;

import com.tobeto.spring.b.entities.County;
import com.tobeto.spring.b.services.dtos.responses.county.GetCountyListResponse;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CountyRepository extends JpaRepository<County,Integer> {
    List<County>  findByNameLike(String name);
    County findFirstByNameStartingWithOrderByName(String name);
}
