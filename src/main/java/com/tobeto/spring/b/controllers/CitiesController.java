package com.tobeto.spring.b.controllers;

import com.tobeto.spring.b.entities.Brand;
import com.tobeto.spring.b.entities.City;
import com.tobeto.spring.b.repositories.CityRepository;
import org.springframework.web.bind.annotation.*;

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
    public List<City> getAll(){
        return cityRepository.findAll();
    }
    @GetMapping("{id}")
    public City getById(@PathVariable int id){
        return cityRepository.findById(id).orElseThrow();
    }
    @PostMapping
    public void add(@RequestBody City city){
        cityRepository.save(city);
    }
    @PutMapping("{id}")
    public City update(@RequestBody City newCity,@PathVariable int id){
        Optional<City> city= cityRepository.findById(id);
        if (city.isPresent()){
            City foundCity=city.get();
            foundCity.setId(newCity.getId());
            foundCity.setName(newCity.getName());
            cityRepository.save(foundCity);
            return foundCity;
        }
        else {
            return null;
        }
    }
    @DeleteMapping("{id}")
    public void delete(@PathVariable int id){
        cityRepository.deleteById(id);
    }
}
