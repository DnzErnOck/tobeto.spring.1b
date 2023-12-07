package com.tobeto.spring.b.services.dtos.responses.county;

import com.tobeto.spring.b.services.dtos.responses.city.GetCityListResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetCountyListResponse {
    private String name;
    private GetCityListResponse getCityListResponse;
}
