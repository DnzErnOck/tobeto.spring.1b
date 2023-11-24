package com.tobeto.spring.b.dtos.requests.address;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddAddressRequest {
    private String postalCode;
    private String addressDetail;
}
