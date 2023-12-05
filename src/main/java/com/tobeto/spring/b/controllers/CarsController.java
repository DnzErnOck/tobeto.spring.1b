package com.tobeto.spring.b.controllers;

import com.tobeto.spring.b.services.abstracts.CarService;
import com.tobeto.spring.b.services.dtos.requests.car.AddCarRequest;
import com.tobeto.spring.b.services.dtos.requests.car.UpdateCarRequest;
import com.tobeto.spring.b.services.dtos.responses.car.GetCarListResponse;
import com.tobeto.spring.b.services.dtos.responses.car.GetCarResponse;

import com.tobeto.spring.b.entities.Car;
import com.tobeto.spring.b.repositories.CarRepository;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/cars")
@AllArgsConstructor
public class CarsController {
    private final CarService carService;

    @GetMapping
    public List<GetCarListResponse> getAll() {
        return carService.getAll();
    }

    @GetMapping("{id}")
    public GetCarResponse getById(@PathVariable int id) {
        return carService.getById(id);
    }

    @PostMapping
    public void add(@RequestBody AddCarRequest addCarRequest) {
        carService.add(addCarRequest);
    }
    @PutMapping("{id}")
    public void update(@RequestBody UpdateCarRequest updateCarRequest, @PathVariable int id){
        carService.update(updateCarRequest,id);
    }
    @DeleteMapping("{id}")
    public void delete(@PathVariable int id){
        carService.delete(id);
    }



}
