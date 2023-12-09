package com.tobeto.spring.b.services.dtos.requests.bill;

import jakarta.validation.constraints.Negative;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddBillRequest {
    @Positive(message = "sadece pozitif değer verilebilir.")
    private double totalPrice;
}
