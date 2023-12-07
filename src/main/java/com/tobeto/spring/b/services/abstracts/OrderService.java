package com.tobeto.spring.b.services.abstracts;

import com.tobeto.spring.b.services.dtos.requests.order.AddOrderRequest;
import com.tobeto.spring.b.services.dtos.requests.order.UpdateOrderRequest;
import com.tobeto.spring.b.services.dtos.responses.order.GetOrderListResponse;
import com.tobeto.spring.b.services.dtos.responses.order.GetOrderResponse;

import java.time.LocalDate;
import java.util.List;

public interface OrderService {
    List<GetOrderListResponse> getAll();
    GetOrderResponse getById(int id);
    void add(AddOrderRequest addOrderRequest);
    void update(UpdateOrderRequest updateOrderRequest,int id);
    void delete(int id);
    List<GetOrderListResponse> getAllByStartDateBetween(LocalDate date1, LocalDate date2);
}
