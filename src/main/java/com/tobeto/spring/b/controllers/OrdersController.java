package com.tobeto.spring.b.controllers;
import com.tobeto.spring.b.services.abstracts.OrderService;
import com.tobeto.spring.b.services.dtos.requests.order.AddOrderRequest;
import com.tobeto.spring.b.services.dtos.requests.order.UpdateOrderRequest;
import com.tobeto.spring.b.services.dtos.responses.order.GetOrderListResponse;
import com.tobeto.spring.b.services.dtos.responses.order.GetOrderResponse;
import com.tobeto.spring.b.entities.Order;
import com.tobeto.spring.b.repositories.OrderRepository;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/order")
@AllArgsConstructor
public class OrdersController {
    private final OrderService orderService;

    @GetMapping
    public List<GetOrderListResponse> getAll(){
        return orderService.getAll();
    }
    @GetMapping("{id}")
    public GetOrderResponse getById(@PathVariable int id){
       return orderService.getById(id);
    }
    @PostMapping
    public void add(@RequestBody AddOrderRequest addOrderRequest){
        orderService.add(addOrderRequest);
    }
    @PutMapping("{id}")
    public void update(@RequestBody UpdateOrderRequest updateOrderRequest, @PathVariable int id){
        orderService.update(updateOrderRequest,id);
    }
    @DeleteMapping("{id}")
    public void delete(@PathVariable int id){
        orderService.delete(id);
    }

    @GetMapping("getStartDateBetween")
    public List<GetOrderListResponse> getAllByStartDateBetween(@RequestParam LocalDate date1, @RequestParam LocalDate date2){
        return orderService.getAllByStartDateBetween(date1, date2);
    }


}
