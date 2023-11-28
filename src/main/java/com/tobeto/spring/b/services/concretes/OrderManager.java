package com.tobeto.spring.b.services.concretes;

import com.tobeto.spring.b.entities.Order;
import com.tobeto.spring.b.repositories.OrderRepository;
import com.tobeto.spring.b.services.abstracts.OrderService;
import com.tobeto.spring.b.services.dtos.requests.order.AddOrderRequest;
import com.tobeto.spring.b.services.dtos.requests.order.UpdateOrderRequest;
import com.tobeto.spring.b.services.dtos.responses.order.GetOrderListResponse;
import com.tobeto.spring.b.services.dtos.responses.order.GetOrderResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class OrderManager implements OrderService {
    private final OrderRepository orderRepository;

    @Override
    public List<GetOrderListResponse> getAll() {
        List<Order> orderList =  orderRepository.findAll();
        List<GetOrderListResponse> getOrderListResponseList = new ArrayList<>();
        for (Order order:orderList) {
            GetOrderListResponse response = new GetOrderListResponse();
            response.setTotalPrice(order.getTotalPrice());
            response.setPaymentType(order.getPaymentType());
            getOrderListResponseList.add(response);
        }
        return getOrderListResponseList;
    }

    @Override
    public GetOrderResponse getById(int id) {
        Order order = orderRepository.findById(id).orElseThrow();
        GetOrderResponse getOrderResponse = new GetOrderResponse();
        getOrderResponse.setPaymentType(order.getPaymentType());
        getOrderResponse.setTotalPrice(order.getTotalPrice());
        return getOrderResponse;
    }

    @Override
    public void add(AddOrderRequest addOrderRequest) {
        Order order = new Order();
        order.setTotalPrice(addOrderRequest.getTotalPrice());
        order.setPaymentType(addOrderRequest.getPaymentType());
        orderRepository.save(order);
    }

    @Override
    public void update(UpdateOrderRequest updateOrderRequest, int id) {
        Optional<Order> order= orderRepository.findById(id);
        if (order.isPresent()){
            Order foundOrder=order.get();
            foundOrder.setPaymentType(updateOrderRequest.getPaymentType());
            foundOrder.setTotalPrice(updateOrderRequest.getTotalPrice());
            orderRepository.save(foundOrder);
        }
    }

    @Override
    public void delete(int id) {
        orderRepository.deleteById(id);
    }
}
