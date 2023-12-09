package com.tobeto.spring.b.repositories;

import com.tobeto.spring.b.entities.County;
import com.tobeto.spring.b.services.dtos.responses.county.GetCountyListResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CountyRepository extends JpaRepository<County,Integer> {
    List<County>  findByNameLike(String name);
    County findFirstByNameStartingWithOrderByName(String name);

    @Query("select new com.tobeto.spring.b.services.dtos.responses.county.GetCountyListResponse(c.name," +
            "new com.tobeto.spring.b.services.dtos.responses.city.GetCityListResponse(ct.name)) from County c inner join c.city ct where c.name =:name")
    List<GetCountyListResponse> findByCountyName(String name);

    @Query("select new com.tobeto.spring.b.services.dtos.responses.county.GetCountyListResponse(c.name," +
            "new com.tobeto.spring.b.services.dtos.responses.city.GetCityListResponse(ct.name)) from County c inner join c.city ct where ct.name =:name")
    List<GetCountyListResponse> findByCityName(String name);
}
