package com.tobeto.spring.b.controllers;

import com.tobeto.spring.b.services.abstracts.CountyService;
import com.tobeto.spring.b.services.dtos.requests.county.AddCountyRequest;
import com.tobeto.spring.b.services.dtos.requests.county.UpdateCountyRequest;
import com.tobeto.spring.b.services.dtos.responses.county.GetCountyListResponse;
import com.tobeto.spring.b.services.dtos.responses.county.GetCountyResponse;
import com.tobeto.spring.b.entities.County;
import com.tobeto.spring.b.repositories.CountyRepository;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/county")
@AllArgsConstructor
public class CountiesController {
    private final CountyService countyService;

    @GetMapping
    public List<GetCountyListResponse> getAll(){
        return countyService.getAll();
    }
    @GetMapping("{id}")
    public GetCountyResponse getById(@PathVariable int id){
        return countyService.getById(id);
    }
    @PostMapping
    public void add(@RequestBody AddCountyRequest addCountyRequest){
        countyService.add(addCountyRequest);
    }
    @PutMapping("{id}")
    public void update(@RequestBody UpdateCountyRequest updateCountyRequest, @PathVariable int id){
        countyService.update(updateCountyRequest,id);
    }
    @DeleteMapping("{id}")
    public void delete(@PathVariable int id){
        countyService.delete(id);
    }
    @GetMapping("name/{name}")
    public List<GetCountyListResponse> getAllByName(@PathVariable String name){
        return countyService.getAllByName(name);
    }
    @GetMapping("nameStarting/{name}")
    public GetCountyResponse getStartByName(@PathVariable String name){
        return countyService.getStartByName(name);
    }
}
