package com.tobeto.spring.b.services.dtos.responses.order;

import com.tobeto.spring.b.services.dtos.responses.customer.GetCustomerResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetOrderResponse {
    private double totalPrice;
    private String paymentType;
    private GetCustomerResponse customer;
}
