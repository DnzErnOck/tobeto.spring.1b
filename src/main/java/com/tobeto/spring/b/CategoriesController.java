package com.tobeto.spring.b;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/categories")
public class CategoriesController {
    List<Category> inMemoryCategoriesList = new ArrayList<>();

    @PostMapping
    public Category add(@RequestBody Category category){
        inMemoryCategoriesList.add(category);
        return category;
    }

    @GetMapping
    public List<Category> get(){
        return inMemoryCategoriesList;
    }

    @GetMapping("{id}")
    public Optional<Category> getById(@PathVariable int id){
        return inMemoryCategoriesList.stream()
                .filter((c)->c.getId() == id)
                .findFirst();
    }

    @PutMapping("{id}")
    public Category update(@PathVariable int id,@RequestBody Category category) throws Exception {
        Optional<Category> category1 = getById(id);
        if (category1.isPresent()){
            Category category2 = category1.get();
            category2.setCategoryName(category.getCategoryName());
            category2.setCategoryType(category.getCategoryType());
            return category2;
        }
        else {
            throw new Exception("Kategori bulunamadı.");
        }
    }

    @DeleteMapping("{id}")
    public boolean delete(@PathVariable int id){
        Optional<Category> category1 = getById(id);
        if (category1.isPresent()){
            Category category = category1.get();
            inMemoryCategoriesList.remove(category);
            return true;
        }
            return false;
    }

}
