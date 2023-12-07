package com.tobeto.spring.b.services.dtos.responses.order;

import com.tobeto.spring.b.entities.Customer;
import com.tobeto.spring.b.services.dtos.responses.customer.GetListCustomerResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetOrderListResponse {
    private double totalPrice;
    private LocalDate startDate;
    //private GetListCustomerResponse customer;
}
