package com.tobeto.spring.b.controllers;

import com.tobeto.spring.b.dtos.requests.city.AddCityRequest;
import com.tobeto.spring.b.dtos.requests.city.UpdateCityRequest;
import com.tobeto.spring.b.dtos.responses.car.GetCarListResponse;
import com.tobeto.spring.b.dtos.responses.car.GetCarResponse;
import com.tobeto.spring.b.dtos.responses.city.GetCityListResponse;
import com.tobeto.spring.b.dtos.responses.city.GetCityResponse;
import com.tobeto.spring.b.entities.Brand;
import com.tobeto.spring.b.entities.Car;
import com.tobeto.spring.b.entities.City;
import com.tobeto.spring.b.repositories.CityRepository;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/city")
public class CitiesController {
    private final CityRepository cityRepository;

    public CitiesController(CityRepository cityRepository) {
        this.cityRepository = cityRepository;
    }

    @GetMapping
    public List<GetCityListResponse> getAll(){
        List<City> cityList= cityRepository.findAll();
        List<GetCityListResponse> getCityListResponseList=new ArrayList<>();
        for (City city: cityList) {
            GetCityListResponse response = new GetCityListResponse();
            response.setName(city.getName());
            getCityListResponseList.add(response);
        }
        return getCityListResponseList;

    }
    @GetMapping("{id}")
    public GetCityResponse getById(@PathVariable int id){
        City city = cityRepository.findById(id).orElseThrow();
        GetCityResponse getCityResponse =new GetCityResponse();
        getCityResponse.setName(city.getName());
        return getCityResponse;
    }
    @PostMapping
    public void add(@RequestBody AddCityRequest addCityRequest){
        City city = new City();
        city.setName(addCityRequest.getName());
        cityRepository.save(city);
    }
    @PutMapping("{id}")
    public void update(@RequestBody UpdateCityRequest updateCityRequest, @PathVariable int id){
        Optional<City> city= cityRepository.findById(id);
        if (city.isPresent()){
            City foundCity=city.get();
            foundCity.setName(updateCityRequest.getName());
            cityRepository.save(foundCity);
        }
    }
    @DeleteMapping("{id}")
    public void delete(@PathVariable int id){
        cityRepository.deleteById(id);
    }
}
