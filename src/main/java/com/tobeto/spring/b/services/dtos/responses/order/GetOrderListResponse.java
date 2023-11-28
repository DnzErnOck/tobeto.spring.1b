package com.tobeto.spring.b.services.dtos.responses.order;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetOrderListResponse {
    private double totalPrice;
    private String paymentType;
}
