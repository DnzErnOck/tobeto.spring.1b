package com.tobeto.spring.b.services.dtos.requests.address;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddAddressRequest {
    @NotBlank(message = "Posta kodu boş geçilemez.")
    private String postalCode;
    private String addressDetail;
}
