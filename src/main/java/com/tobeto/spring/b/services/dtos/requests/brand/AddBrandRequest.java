package com.tobeto.spring.b.services.dtos.requests.brand;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddBrandRequest {
    @NotBlank(message = "marka adı boş olamaz")//multi-language
    //@Length(min = 3, max =20)
    @Size(min = 3, max =20,message = "marka adı 3 ile 20 hane arasında olmalıdır")
    private String name;
}
