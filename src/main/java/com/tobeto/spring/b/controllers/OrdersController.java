package com.tobeto.spring.b.controllers;
import com.tobeto.spring.b.entities.Order;
import com.tobeto.spring.b.repositories.OrderRepository;
import org.springframework.web.bind.annotation.*;
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
    public List<Order> getAll(){
        return orderRepository.findAll();
    }
    @GetMapping("{id}")
    public Order getById(@PathVariable int id){
        return orderRepository.findById(id).orElseThrow();
    }
    @PostMapping
    public void add(@RequestBody Order order){
        orderRepository.save(order);
    }
    @PutMapping("{id}")
    public Order update(@RequestBody Order newOrder,@PathVariable int id){
        Optional<Order> order= orderRepository.findById(id);
        if (order.isPresent()){
            Order foundOrder=order.get();
            foundOrder.setId(newOrder.getId());
            foundOrder.setOrderDate(newOrder.getOrderDate());
            foundOrder.setStartDate(newOrder.getStartDate());
            foundOrder.setFinishDate(newOrder.getFinishDate());
            foundOrder.setTotalPrice(newOrder.getTotalPrice());
            foundOrder.setPaymentType(newOrder.getPaymentType());
            orderRepository.save(foundOrder);
            return foundOrder;
        }
        else {
            return null;
        }
    }
    @DeleteMapping("{id}")
    public void delete(@PathVariable int id){
        orderRepository.deleteById(id);
    }

}
