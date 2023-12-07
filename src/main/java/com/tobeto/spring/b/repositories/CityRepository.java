package com.tobeto.spring.b.repositories;

import com.tobeto.spring.b.entities.City;
import com.tobeto.spring.b.services.dtos.responses.city.GetCityListResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CityRepository extends JpaRepository<City,Integer> {
    City findFirstByNameNotOrderByNameDesc(String name);
    City findFirstByNameNotLikeOrderByNameDesc(String name);

    //jpql
    @Query("select new com.tobeto.spring.b.services.dtos.responses.city.GetCityListResponse(c.name) from City c where c.name like %:name%")
    List<GetCityListResponse> findByName(String name);

    @Query("select new com.tobeto.spring.b.services.dtos.responses.city.GetCityListResponse(c.name) from City c where c.name in (:name1,:name2)")
    List<GetCityListResponse> findInName(String name1, String name2);



}
