package com.tobeto.spring.b.services.abstracts;

import com.tobeto.spring.b.entities.City;
import com.tobeto.spring.b.services.dtos.requests.city.AddCityRequest;
import com.tobeto.spring.b.services.dtos.requests.city.UpdateCityRequest;
import com.tobeto.spring.b.services.dtos.responses.city.GetCityListResponse;
import com.tobeto.spring.b.services.dtos.responses.city.GetCityResponse;

import java.util.List;

public interface CityService {
    List<GetCityListResponse> getAll();
    GetCityResponse getById(int id);
    void add(AddCityRequest addCityRequest);
    void update(UpdateCityRequest updateCityRequest,int id);
    void delete(int id);
    GetCityResponse notName(String name);
    GetCityResponse nameNotLike(String name);

    List<GetCityListResponse> getByName(String name);
    List<GetCityListResponse> getInName(String name1,String name2);
}
