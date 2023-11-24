package com.tobeto.spring.b.dtos.responses.address;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetAddressListResponse {
    private String postalCode;
    private String addressDetail;
}
