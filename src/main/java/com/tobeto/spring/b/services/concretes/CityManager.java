package com.tobeto.spring.b.services.concretes;

import com.tobeto.spring.b.entities.City;
import com.tobeto.spring.b.repositories.CityRepository;
import com.tobeto.spring.b.services.abstracts.CityService;
import com.tobeto.spring.b.services.dtos.requests.city.AddCityRequest;
import com.tobeto.spring.b.services.dtos.requests.city.UpdateCityRequest;
import com.tobeto.spring.b.services.dtos.responses.city.GetCityListResponse;
import com.tobeto.spring.b.services.dtos.responses.city.GetCityResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CityManager implements CityService {
    private final CityRepository cityRepository;

    @Override
    public List<GetCityListResponse> getAll() {
        List<City> cityList= cityRepository.findAll();
        List<GetCityListResponse> getCityListResponseList=new ArrayList<>();
        for (City city: cityList) {
            GetCityListResponse response = new GetCityListResponse();
            response.setName(city.getName());
            getCityListResponseList.add(response);
        }
        return getCityListResponseList;
    }

    @Override
    public GetCityResponse getById(int id) {
        City city = cityRepository.findById(id).orElseThrow();
        GetCityResponse getCityResponse =new GetCityResponse();
        getCityResponse.setName(city.getName());
        return getCityResponse;
    }

    @Override
    public void add(AddCityRequest addCityRequest) {
        City city = new City();
        city.setName(addCityRequest.getName());
        cityRepository.save(city);
    }

    @Override
    public void update(UpdateCityRequest updateCityRequest, int id) {
        Optional<City> city= cityRepository.findById(id);
        if (city.isPresent()){
            City foundCity=city.get();
            foundCity.setName(updateCityRequest.getName());
            cityRepository.save(foundCity);
        }
    }

    @Override
    public void delete(int id) {
        cityRepository.deleteById(id);
    }

    @Override
    public GetCityResponse notName(String name) {
        City city=cityRepository.findFirstByNameNotOrderByNameDesc(name);
        GetCityResponse getCityResponse =new GetCityResponse();
        getCityResponse.setName(city.getName());
        return getCityResponse;
    }

    @Override
    public GetCityResponse nameNotLike(String name) {
        City city =cityRepository.findFirstByNameNotLikeOrderByNameDesc("%"+name+"%");
        GetCityResponse getCityResponse =new GetCityResponse();
        getCityResponse.setName(city.getName());
        return getCityResponse;
    }
}
