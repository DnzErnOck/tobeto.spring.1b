package com.tobeto.spring.b.dtos.responses.bill;

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
}
