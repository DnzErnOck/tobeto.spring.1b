package com.tobeto.spring.b.services.dtos.responses.county;

import com.tobeto.spring.b.services.dtos.responses.city.GetCityResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetCountyResponse {
    private String name;
    private GetCityResponse city;
}
