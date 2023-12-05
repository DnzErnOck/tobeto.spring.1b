package com.tobeto.spring.b.controllers;

import com.tobeto.spring.b.services.abstracts.BrandService;
import com.tobeto.spring.b.services.dtos.requests.brand.AddBrandRequest;
import com.tobeto.spring.b.services.dtos.requests.brand.UpdateBrandRequest;
import com.tobeto.spring.b.services.dtos.responses.brand.GetBrandListResponse;
import com.tobeto.spring.b.services.dtos.responses.brand.GetBrandResponse;
import com.tobeto.spring.b.entities.Brand;
import com.tobeto.spring.b.repositories.BrandRepository;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/brands")
@AllArgsConstructor
public class BrandsController {

    private final BrandService brandService;
    @GetMapping
    //List<BrandForListiningDto> => id,name
    public List<GetBrandListResponse> getAll(@RequestBody GetBrandListResponse getBrandListResponse) {
       return this.brandService.getAll();
    }
    @GetMapping("{id}")
    public GetBrandResponse getById(@PathVariable int id){
        return this.brandService.getById(id);
    }


    @PostMapping
    public void add(@RequestBody AddBrandRequest addBrandRequest){
        this.brandService.add(addBrandRequest);
    }
    @PutMapping("{id}")
    public void update(@RequestBody UpdateBrandRequest updateBrandRequest,@PathVariable int id){
        this.brandService.update(updateBrandRequest,id);
    }
    @DeleteMapping("{id}")
    public void delete(@PathVariable int id){
        this.brandService.delete(id);
    }

    @GetMapping("getByName")
    public List<GetBrandListResponse> getByName(@RequestParam String name,@RequestParam int id){
        return this.brandService.getByName(name,id);
    }
    @GetMapping("search")
    public List<GetBrandListResponse> search(@RequestParam String name){
        return this.brandService.search(name);
    }
}
//DTO -> DATA TRANSFER OBJECT
//her istek için request bir responce modeli bulunmaktadır
//AddBrandResponce add(AddBrandResponce request)
