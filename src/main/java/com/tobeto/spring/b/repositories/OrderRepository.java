package com.tobeto.spring.b.repositories;

import com.tobeto.spring.b.entities.Order;
import com.tobeto.spring.b.services.dtos.responses.order.GetOrderListResponse;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface OrderRepository extends JpaRepository<Order,Integer> {
    List<Order>  findByStartDateBetween(LocalDate date1,LocalDate date2);
}
