package com.tobeto.spring.b.services.dtos.responses.car;

import com.tobeto.spring.b.entities.Brand;
import com.tobeto.spring.b.services.dtos.responses.brand.GetBrandListResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetCarListResponse {
    private String modelName;
    private String color;
    private String shiftType;
    private GetBrandListResponse brand;
}
