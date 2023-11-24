package com.tobeto.spring.b.controllers;
import com.tobeto.spring.b.dtos.requests.order.AddOrderRequest;
import com.tobeto.spring.b.dtos.requests.order.UpdateOrderRequest;
import com.tobeto.spring.b.dtos.responses.bill.GetBillListResponse;
import com.tobeto.spring.b.dtos.responses.bill.GetBillResponse;
import com.tobeto.spring.b.dtos.responses.order.GetOrderListResponse;
import com.tobeto.spring.b.dtos.responses.order.GetOrderResponse;
import com.tobeto.spring.b.entities.Bill;
import com.tobeto.spring.b.entities.Order;
import com.tobeto.spring.b.repositories.OrderRepository;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/order")
public class OrdersController {
    private final OrderRepository orderRepository;

    public OrdersController(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @GetMapping
    public List<GetOrderListResponse> getAll(){
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
    @GetMapping("{id}")
    public GetOrderResponse getById(@PathVariable int id){
        Order order = orderRepository.findById(id).orElseThrow();
        GetOrderResponse getOrderResponse = new GetOrderResponse();
        getOrderResponse.setPaymentType(order.getPaymentType());
        getOrderResponse.setTotalPrice(order.getTotalPrice());
        return getOrderResponse;
    }
    @PostMapping
    public void add(@RequestBody AddOrderRequest addOrderRequest){
        Order order = new Order();
        order.setTotalPrice(addOrderRequest.getTotalPrice());
        order.setPaymentType(addOrderRequest.getPaymentType());
        orderRepository.save(order);
    }
    @PutMapping("{id}")
    public void update(@RequestBody UpdateOrderRequest updateOrderRequest, @PathVariable int id){
        Optional<Order> order= orderRepository.findById(id);
        if (order.isPresent()){
            Order foundOrder=order.get();
            foundOrder.setPaymentType(updateOrderRequest.getPaymentType());
            foundOrder.setTotalPrice(updateOrderRequest.getTotalPrice());
            orderRepository.save(foundOrder);
        }
    }
    @DeleteMapping("{id}")
    public void delete(@PathVariable int id){
        orderRepository.deleteById(id);
    }

}
