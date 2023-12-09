package com.tobeto.spring.b.services.concretes;

import com.tobeto.spring.b.entities.Order;
import com.tobeto.spring.b.repositories.OrderRepository;
import com.tobeto.spring.b.services.abstracts.OrderService;
import com.tobeto.spring.b.services.dtos.requests.order.AddOrderRequest;
import com.tobeto.spring.b.services.dtos.requests.order.UpdateOrderRequest;
import com.tobeto.spring.b.services.dtos.responses.customer.GetCustomerResponse;
import com.tobeto.spring.b.services.dtos.responses.customer.GetListCustomerResponse;
import com.tobeto.spring.b.services.dtos.responses.order.GetOrderListResponse;
import com.tobeto.spring.b.services.dtos.responses.order.GetOrderResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
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
            response.setStartDate(order.getStartDate());
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
        if (addOrderRequest.getPaymentType().equals("kart")){
            throw new RuntimeException("Kart ile ödeme alınmamaktadır. Lütfen farklı bir ödeme yöntemi giriniz.");
        }
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

    @Override
    public List<GetOrderListResponse> getAllByStartDateBetween(LocalDate date1, LocalDate date2) {
        List<Order> billList = orderRepository.findByStartDateBetween(date1, date2);
        List<GetOrderListResponse> getOrderListResponseList = new ArrayList<>();
        for (Order order:billList) {
            GetOrderListResponse response = new GetOrderListResponse();
            //GetListCustomerResponse responseCustomer = new GetListCustomerResponse(order.getCustomer().getName(),order.getCustomer().getSurName(),order.getCustomer().getAge());
            response.setTotalPrice(order.getTotalPrice());
            response.setStartDate(order.getStartDate());
            //response.setCustomer(responseCustomer);
            getOrderListResponseList.add(response);
        }
        return getOrderListResponseList;
    }

    @Override
    public GetOrderResponse getGreaterTotalPrice(double totalPrice) {
        Order order = orderRepository.findFirstByTotalPriceGreaterThanOrderByTotalPriceDesc(totalPrice);
        GetOrderResponse response = new GetOrderResponse();
        GetCustomerResponse customerResponse=new GetCustomerResponse(order.getCustomer().getName(), order.getCustomer().getSurName());
        response.setTotalPrice(order.getTotalPrice());
        response.setPaymentType(order.getPaymentType());
        response.setCustomer(customerResponse);
        return response;
    }

    @Override
    public List<GetOrderListResponse> getPaymentTypeIsNull() {
        return orderRepository.findPaymentTypeIsNull();
    }

    @Override
    public List<GetOrderListResponse> getByNameOrSurname(String name, String surName) {
        return orderRepository.findByNameOrSurname(name, surName);
    }

}
