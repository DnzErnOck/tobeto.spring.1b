package com.tobeto.spring.b.services.concretes;

import com.tobeto.spring.b.entities.Car;
import com.tobeto.spring.b.repositories.CarRepository;
import com.tobeto.spring.b.services.abstracts.CarService;
import com.tobeto.spring.b.services.dtos.requests.car.AddCarRequest;
import com.tobeto.spring.b.services.dtos.requests.car.UpdateCarRequest;
import com.tobeto.spring.b.services.dtos.responses.car.GetCarListResponse;
import com.tobeto.spring.b.services.dtos.responses.car.GetCarResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CarManager implements CarService {
    private final CarRepository carRepository;

    @Override
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

    @Override
    public GetCarResponse getById(int id) {
        Car car = carRepository.findById(id).orElseThrow();
        GetCarResponse getCarResponse =new GetCarResponse();

        getCarResponse.setModelYear(car.getModelYear());
        getCarResponse.setPrice(car.getPrice());
        return getCarResponse;
    }

    @Override
    public void add(AddCarRequest addCarRequest) {
        int minYear = 1995;

        if (addCarRequest.getModelYear() < minYear){
            throw new RuntimeException(minYear + "yılından küçük araba eklenemez.");
        }


        Car car = new Car();
        car.setModelName(addCarRequest.getModelName());
        car.setModelYear(addCarRequest.getModelYear());
        carRepository.save(car);
    }

    @Override
    public void update(UpdateCarRequest updateCarRequest, int id) {
        List<String> validColors = Arrays.asList("Red", "Blue", "Green", "White", "Black");
        if(!validColors.contains(updateCarRequest.getColor())){
            throw new RuntimeException("Geçersiz renk girdiniz. Lütfen geçerli bir renk seçin:\n" + validColors);
        }
        Optional<Car> car= carRepository.findById(id);
        if (car.isPresent()){
            Car foundCar=car.get();
            foundCar.setColor(updateCarRequest.getColor());
            foundCar.setModelName(updateCarRequest.getModelName());
            carRepository.save(foundCar);

        }
    }

    @Override
    public void delete(int id) {
        carRepository.deleteById(id);
    }

    @Override
    public List<GetCarListResponse> getAll2() {
        return carRepository.getAll2();
    }
}
