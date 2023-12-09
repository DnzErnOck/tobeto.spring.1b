package com.tobeto.spring.b.services.concretes;

import com.tobeto.spring.b.entities.County;
import com.tobeto.spring.b.repositories.CountyRepository;
import com.tobeto.spring.b.services.abstracts.CountyService;
import com.tobeto.spring.b.services.dtos.requests.county.AddCountyRequest;
import com.tobeto.spring.b.services.dtos.requests.county.UpdateCountyRequest;
import com.tobeto.spring.b.services.dtos.responses.city.GetCityListResponse;
import com.tobeto.spring.b.services.dtos.responses.city.GetCityResponse;
import com.tobeto.spring.b.services.dtos.responses.county.GetCountyListResponse;
import com.tobeto.spring.b.services.dtos.responses.county.GetCountyResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CountyManager implements CountyService {
    private final CountyRepository countyRepository;


    @Override
    public List<GetCountyListResponse> getAll() {
        List<County> countyList= countyRepository.findAll();
        List<GetCountyListResponse> getCountyListResponseList=new ArrayList<>();
        for (County county: countyList) {
            GetCountyListResponse response = new GetCountyListResponse();
            response.setName(county.getName());
            getCountyListResponseList.add(response);
        }
        return getCountyListResponseList;
    }

    @Override
    public GetCountyResponse getById(int id) {
        County county = countyRepository.findById(id).orElseThrow();
        GetCountyResponse getCountyResponse =new GetCountyResponse();
        getCountyResponse.setName(county.getName());
        return getCountyResponse;
    }

    @Override
    public void add(AddCountyRequest addCountyRequest) {
        County county = new County();
        county.setName(addCountyRequest.getName());
        countyRepository.save(county);
    }

    @Override
    public void update(UpdateCountyRequest updateCountyRequest, int id) {
        Optional<County> county= countyRepository.findById(id);
        if (county.isPresent()){
            County foundCounty=county.get();
            foundCounty.setName(updateCountyRequest.getName());
            countyRepository.save(foundCounty);
        }
    }

    @Override
    public void delete(int id) {
        countyRepository.deleteById(id);
    }

    @Override
    public List<GetCountyListResponse> getAllByName(String name) {
        List<County> countyList=countyRepository.findByNameLike("%"+name+"%");
        List<GetCountyListResponse> getCountyListResponseList=new ArrayList<>();
        for (County county : countyList) {
            GetCountyListResponse response = new GetCountyListResponse();
            GetCityListResponse cityListResponse = new GetCityListResponse(county.getCity().getName());
            response.setName(county.getName());
            response.setCity(cityListResponse);
            getCountyListResponseList.add(response);
        }
        return getCountyListResponseList;
    }

    @Override
    public GetCountyResponse getStartByName(String name) {
        County county=countyRepository.findFirstByNameStartingWithOrderByName(name);
        GetCountyResponse response=new GetCountyResponse();
        GetCityResponse city=new GetCityResponse(county.getCity().getName());
        response.setName(county.getName());
        response.setCity(city);
        return response;
    }

    @Override
    public List<GetCountyListResponse> getByCountName(String name) {
        return countyRepository.findByCountyName(name);
    }

    @Override
    public List<GetCountyListResponse> getByCityName(String name) {
        return countyRepository.findByCityName(name);
    }
}
