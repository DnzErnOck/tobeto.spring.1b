package com.tobeto.spring.b.dtos.requests.address;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateAddressRequest {
    private String postalCode;
    private String addressDetail;
}
