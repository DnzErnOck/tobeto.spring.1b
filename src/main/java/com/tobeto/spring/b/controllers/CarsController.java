package com.tobeto.spring.b.controllers;

import com.tobeto.spring.b.dtos.requests.car.AddCarRequest;
import com.tobeto.spring.b.dtos.requests.car.UpdateCarRequest;
import com.tobeto.spring.b.dtos.responses.car.GetCarListResponse;
import com.tobeto.spring.b.dtos.responses.car.GetCarResponse;

import com.tobeto.spring.b.entities.Car;
import com.tobeto.spring.b.repositories.CarRepository;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/cars")
public class CarsController {
    private final CarRepository carRepository;

    public CarsController(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    @GetMapping
    public List<GetCarListResponse> getAll() {
        List<Car> carList= carRepository.findAll();
        List<GetCarListResponse> getCarListResponses=new ArrayList<>();
        for (Car car: carList) {
            GetCarListResponse response = new GetCarListResponse();
            response.setModelName(car.getModelName());
            response.setColor(car.getColor());
            response.setShiftType(car.getShiftType());
            getCarListResponses.add(response);
        }
        return getCarListResponses;
    }

    @GetMapping("{id}")
    public GetCarResponse getById(@PathVariable int id) {
        Car car = carRepository.findById(id).orElseThrow();
        GetCarResponse getCarResponse =new GetCarResponse();

        getCarResponse.setModelYear(car.getModelYear());
        getCarResponse.setPrice(car.getPrice());
        return getCarResponse;
    }

    @PostMapping
    public void add(@RequestBody AddCarRequest carForAddDto) {
        Car car = new Car();
        car.setModelName(carForAddDto.getModelName());
        car.setModelYear(carForAddDto.getModelYear());
        carRepository.save(car);
    }
    @PutMapping("{id}")
    public void update(@RequestBody UpdateCarRequest updateCarRequest, @PathVariable int id){
        Optional<Car> car= carRepository.findById(id);
        if (car.isPresent()){
            Car foundCar=car.get();
            foundCar.setColor(updateCarRequest.getColor());
            foundCar.setModelName(updateCarRequest.getModelName());
            carRepository.save(foundCar);

        }
    }
    @DeleteMapping("{id}")
    public void delete(@PathVariable int id){
        carRepository.deleteById(id);
    }
}
