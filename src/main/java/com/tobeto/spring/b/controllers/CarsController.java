package com.tobeto.spring.b.controllers;

import com.tobeto.spring.b.entities.Brand;
import com.tobeto.spring.b.entities.Car;
import com.tobeto.spring.b.repositories.CarRepository;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import org.springframework.web.bind.annotation.*;

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
    public List<Car> getAll() {
        return carRepository.findAll();
    }

    @GetMapping("{id}")
    public Car getById(@PathVariable int id) {
        return carRepository.findById(id).orElseThrow();
    }

    @PostMapping
    public void add(@RequestBody Car car) {
        carRepository.save(car);
    }
    @PutMapping("{id}")
    public Car update(@RequestBody Car newCar,@PathVariable int id){
        Optional<Car> car= carRepository.findById(id);
        if (car.isPresent()){
            Car foundCar=car.get();
            foundCar.setId(newCar.getId());
            foundCar.setModelYear(newCar.getModelYear());
            foundCar.setModelName(newCar.getModelName());
            foundCar.setColor(newCar.getColor());
            foundCar.setFuelType(newCar.getFuelType());
            foundCar.setShiftType(newCar.getShiftType());
            foundCar.setPrice(newCar.getPrice());
            carRepository.save(foundCar);
            return foundCar;
        }
        else {
            return null;
        }
    }
    @DeleteMapping("{id}")
    public void delete(@PathVariable int id){
        carRepository.deleteById(id);
    }
}
