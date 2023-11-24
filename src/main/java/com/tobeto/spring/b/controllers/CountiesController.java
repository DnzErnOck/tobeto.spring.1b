package com.tobeto.spring.b.controllers;

import com.tobeto.spring.b.dtos.requests.county.AddCountyRequest;
import com.tobeto.spring.b.dtos.requests.county.UpdateCountyRequest;
import com.tobeto.spring.b.dtos.responses.city.GetCityListResponse;
import com.tobeto.spring.b.dtos.responses.city.GetCityResponse;
import com.tobeto.spring.b.dtos.responses.county.GetCountyListResponse;
import com.tobeto.spring.b.dtos.responses.county.GetCountyResponse;
import com.tobeto.spring.b.entities.Brand;
import com.tobeto.spring.b.entities.City;
import com.tobeto.spring.b.entities.County;
import com.tobeto.spring.b.repositories.CountyRepository;
import org.hibernate.result.UpdateCountOutput;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/county")
public class CountiesController {
    private final CountyRepository countyRepository;

    public CountiesController(CountyRepository countyRepository) {
        this.countyRepository = countyRepository;
    }

    @GetMapping
    public List<GetCountyListResponse> getAll(){
        List<County> countyList= countyRepository.findAll();
        List<GetCountyListResponse> getCountyListResponseList=new ArrayList<>();
        for (County county: countyList) {
            GetCountyListResponse response = new GetCountyListResponse();
            response.setName(county.getName());
            getCountyListResponseList.add(response);
        }
        return getCountyListResponseList;
    }
    @GetMapping("{id}")
    public GetCountyResponse getById(@PathVariable int id){
        County county = countyRepository.findById(id).orElseThrow();
        GetCountyResponse getCountyResponse =new GetCountyResponse();
        getCountyResponse.setName(county.getName());
        return getCountyResponse;
    }
    @PostMapping
    public void add(@RequestBody AddCountyRequest addCountyRequest){
        County county = new County();
        county.setName(addCountyRequest.getName());
        countyRepository.save(county);
    }
    @PutMapping("{id}")
    public void update(@RequestBody UpdateCountyRequest updateCountyRequest, @PathVariable int id){
        Optional<County> county= countyRepository.findById(id);
        if (county.isPresent()){
            County foundCounty=county.get();
            foundCounty.setName(updateCountyRequest.getName());
            countyRepository.save(foundCounty);
        }
    }
    @DeleteMapping("{id}")
    public void delete(@PathVariable int id){
        countyRepository.deleteById(id);
    }
}
