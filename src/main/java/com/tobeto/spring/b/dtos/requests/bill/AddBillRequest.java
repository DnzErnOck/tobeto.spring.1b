package com.tobeto.spring.b.dtos.requests.bill;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddBillRequest {
    private double totalPrice;
}
