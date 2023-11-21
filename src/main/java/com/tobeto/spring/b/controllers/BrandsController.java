package com.tobeto.spring.b.controllers;

import com.tobeto.spring.b.entities.Address;
import com.tobeto.spring.b.entities.Brand;
import com.tobeto.spring.b.repositories.BrandRepository;
import org.springframework.web.bind.annotation.*;

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
    public List<Brand> getAll() {
        return brandRepository.findAll();
    }

    @GetMapping("{id}")
    public Brand getById(@PathVariable int id) {
        // Optional<T> => ilgili filtreden bir veri dönmeyebilir
        return brandRepository.findById(id).orElseThrow();
    }

    @PostMapping
    public void add(@RequestBody Brand brand) {
        brandRepository.save(brand);
    }

    @PutMapping("{id}")
    public Brand update(@RequestBody Brand newBrand,@PathVariable int id) {

        Optional<Brand> brand= brandRepository.findById(id);
        if (brand.isPresent()){
            Brand foundBrand=brand.get();
            foundBrand.setId(newBrand.getId());
            foundBrand.setName(newBrand.getName());
            foundBrand.setType(newBrand.getType());
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
