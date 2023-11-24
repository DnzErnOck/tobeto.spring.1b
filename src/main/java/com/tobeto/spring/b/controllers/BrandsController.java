package com.tobeto.spring.b.controllers;

import com.tobeto.spring.b.dtos.requests.brand.AddBrandRequest;
import com.tobeto.spring.b.dtos.requests.brand.UpdateBrandRequest;
import com.tobeto.spring.b.dtos.responses.brand.GetBrandListResponse;
import com.tobeto.spring.b.dtos.responses.brand.GetBrandResponse;
import com.tobeto.spring.b.dtos.responses.car.GetCarListResponse;
import com.tobeto.spring.b.entities.Brand;
import com.tobeto.spring.b.entities.Car;
import com.tobeto.spring.b.repositories.BrandRepository;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/brands")
public class BrandsController {
    //Dependecy => Bağımlılık
    // Injection => Enjekte
    private final BrandRepository brandRepository;

    //final =>ctor disinda set edemezsiniz.
    public BrandsController(BrandRepository brandRepository) {
        this.brandRepository = brandRepository;
    }

    //spring Ioc container

    @GetMapping
    //List<BrandForListiningDto> => id,name
    public List<GetBrandListResponse> getAll() {
        List<Brand> brandList= brandRepository.findAll();
        List<GetBrandListResponse> getBrandListResponseList=new ArrayList<>();
        for (Brand brand: brandList) {
            GetBrandListResponse response = new GetBrandListResponse();
            response.setName(brand.getName());
            getBrandListResponseList.add(response);
        }
        return getBrandListResponseList;
    }

    @GetMapping("{id}")
    //Brand x
    //BrandForDetailDto
    public GetBrandResponse getById(@PathVariable int id) {
        // Optional<T> => ilgili filtreden bir veri dönmeyebilir
        Brand brand = brandRepository.findById(id).orElseThrow();
        GetBrandResponse dto =new GetBrandResponse();
        dto.setName(brand.getName());
        return dto;
    }

    @PostMapping
    //Brand X
    //BrandForAddDto
    public void add(@RequestBody AddBrandRequest brandForAddDto) {
        //manuel maping => Auto Mapping
        Brand brand =new Brand();
        brand.setName(brandForAddDto.getName());
        brandRepository.save(brand);
    }

    @PutMapping("{id}")
    public Brand update(@RequestBody UpdateBrandRequest updateBrandRequest, @PathVariable int id) {

        Optional<Brand> brand= brandRepository.findById(id);
        if (brand.isPresent()){
            Brand foundBrand=brand.get();
            foundBrand.setName(updateBrandRequest.getName());
            brandRepository.save(foundBrand);
            return foundBrand;
        }
        else {
            return null;
        }
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable int id) {
        //Brand brandToDelete =brandRepository.findById(id).orElseThrow();
        //kod burada geliyorsa excetion fırlatmamıştır.if else gerek kalmıyor
        //brandRepository.delete(brandToDelete);

        //2. yöntem
        //yukarıdakinin yerine işlemleri yapıyor
        brandRepository.deleteById(id);
    }
}
//DTO -> DATA TRANSFER OBJECT
//her istek için request bir responce modeli bulunmaktadır
//AddBrandResponce add(AddBrandResponce request)
