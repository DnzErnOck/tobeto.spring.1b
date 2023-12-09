package com.tobeto.spring.b.services.dtos.requests.city;

import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddCityRequest {
    @Size(min = 2,max = 15,message = "şehir adı minimum 2 maximum 15 karakter olmalıdır.")
    private String name;
}
