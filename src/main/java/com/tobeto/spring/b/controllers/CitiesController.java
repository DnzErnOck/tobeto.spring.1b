package com.tobeto.spring.b.controllers;

import com.tobeto.spring.b.services.abstracts.CityService;
import com.tobeto.spring.b.services.dtos.requests.city.AddCityRequest;
import com.tobeto.spring.b.services.dtos.requests.city.UpdateCityRequest;
import com.tobeto.spring.b.services.dtos.responses.city.GetCityListResponse;
import com.tobeto.spring.b.services.dtos.responses.city.GetCityResponse;
import com.tobeto.spring.b.entities.City;
import com.tobeto.spring.b.repositories.CityRepository;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/city")
@AllArgsConstructor
public class CitiesController {
    private final CityService cityService;

    @GetMapping
    public List<GetCityListResponse> getAll(){
        return cityService.getAll();
    }
    @GetMapping("{id}")
    public GetCityResponse getById(@PathVariable int id){
        return cityService.getById(id);
    }
    @PostMapping
    public void add(@RequestBody AddCityRequest addCityRequest){
        cityService.add(addCityRequest);
    }
    @PutMapping("{id}")
    public void update(@RequestBody UpdateCityRequest updateCityRequest, @PathVariable int id){
        cityService.update(updateCityRequest,id);
    }
    @DeleteMapping("{id}")
    public void delete(@PathVariable int id){
        cityService.delete(id);
    }

    @GetMapping("notName")
    public GetCityResponse notName(@RequestParam String name){
        return cityService.notName(name);
    }
    @GetMapping("nameNotLike")
    public GetCityResponse nameNotLike(@RequestParam String name){
        return cityService.nameNotLike(name);
    }
}
