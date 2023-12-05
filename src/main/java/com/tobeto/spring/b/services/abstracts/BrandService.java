package com.tobeto.spring.b.services.abstracts;

import com.tobeto.spring.b.entities.Brand;
import com.tobeto.spring.b.services.dtos.requests.brand.AddBrandRequest;
import com.tobeto.spring.b.services.dtos.requests.brand.UpdateBrandRequest;
import com.tobeto.spring.b.services.dtos.responses.brand.GetBrandListResponse;
import com.tobeto.spring.b.services.dtos.responses.brand.GetBrandResponse;

import java.util.List;

public interface BrandService {
    void add(AddBrandRequest addBrandRequest);
    GetBrandResponse getById(int id);
    List<GetBrandListResponse> getAll();

    void update(UpdateBrandRequest updateBrandRequest,int id);
    void delete(int id);

    List<GetBrandListResponse> getByName(String name,int id);

    List<Brand> search(String name);


}
