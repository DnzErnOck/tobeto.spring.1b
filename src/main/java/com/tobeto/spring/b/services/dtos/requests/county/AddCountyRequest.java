package com.tobeto.spring.b.services.dtos.requests.county;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddCountyRequest {
    @NotEmpty(message = "İlçe ismi boş geçilemez")
    private String name;
}
