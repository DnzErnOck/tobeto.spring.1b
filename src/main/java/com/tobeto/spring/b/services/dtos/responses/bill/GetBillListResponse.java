package com.tobeto.spring.b.services.dtos.responses.bill;

import com.tobeto.spring.b.services.dtos.responses.customer.GetListCustomerResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetBillListResponse {
    private double totalPrice;
    private LocalDate billDate;
    private GetListCustomerResponse customer;

}
