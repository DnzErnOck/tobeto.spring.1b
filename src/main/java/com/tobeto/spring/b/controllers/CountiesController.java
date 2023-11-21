package com.tobeto.spring.b.controllers;

import com.tobeto.spring.b.entities.Brand;
import com.tobeto.spring.b.entities.County;
import com.tobeto.spring.b.repositories.CountyRepository;
import org.springframework.web.bind.annotation.*;

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
    public List<County> getAll(){
        return countyRepository.findAll();
    }
    @GetMapping("{id}")
    public County getById(@PathVariable int id){
        return countyRepository.findById(id).orElseThrow();
    }
    @PostMapping
    public void add(@RequestBody County county){
        countyRepository.save(county);
    }
    @PutMapping("{id}")
    public County update(@RequestBody County newCounty,@PathVariable int id){
        Optional<County> county= countyRepository.findById(id);
        if (county.isPresent()){
            County foundCounty=county.get();
            foundCounty.setId(newCounty.getId());
            foundCounty.setName(newCounty.getName());
            countyRepository.save(foundCounty);
            return foundCounty;
        }
        else {
            return null;
        }
    }
    @DeleteMapping("{id}")
    public void delete(@PathVariable int id){
        countyRepository.deleteById(id);
    }
}
