package com.tobeto.spring.b.services.dtos.responses.bill;

import com.tobeto.spring.b.services.dtos.responses.address.GetAddressResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetBillResponse {
    private int id;
    private double totalPrice;
    private LocalDate billDate;
    private GetAddressResponse addressResponse;
}
