package com.tobeto.spring.b;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/products")
public class ProductsController {
    List<Product> inMemoryProductList = new ArrayList<>();

    @PostMapping
    public String add(@RequestBody Product product){
        inMemoryProductList.add(product);
        return product.getProductName() + " eklendi";
    }

    @GetMapping
    public List<Product> get(){
        return inMemoryProductList;
    }

    @GetMapping("{id}")
    public Optional<Product> getById(@PathVariable int id){
        return inMemoryProductList.stream()
                .filter((p)->p.getId() == id)
                .findFirst();
    }
    @PutMapping("{id}")
    public Product update(@PathVariable int id,@RequestBody Product product){
        Product product1 = inMemoryProductList.stream()
                .filter((p)->p.getId() == id)
                .findFirst()
                .orElseThrow();
        product1.setProductName(product.getProductName());
        product1.setUnitPrice(product.getUnitPrice());
        return product1;
    }

    @DeleteMapping("{id}")
    public boolean delete(@PathVariable int id){
        Product product = inMemoryProductList.stream()
                .filter((p)->p.getId() == id)
                .findFirst()
                .orElseThrow();
        inMemoryProductList.remove(product);
        return true;
    }

}
