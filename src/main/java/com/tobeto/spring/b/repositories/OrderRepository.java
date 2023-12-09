package com.tobeto.spring.b.repositories;

import com.tobeto.spring.b.entities.Order;
import com.tobeto.spring.b.services.dtos.responses.order.GetOrderListResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface OrderRepository extends JpaRepository<Order,Integer> {
    List<Order>  findByStartDateBetween(LocalDate date1,LocalDate date2);
    Order findFirstByTotalPriceGreaterThanOrderByTotalPriceDesc(double totalPrice);

    @Query("select new com.tobeto.spring.b.services.dtos.responses.order.GetOrderListResponse(o.totalPrice,o.startDate," +
            "new com.tobeto.spring.b.services.dtos.responses.customer.GetListCustomerResponse(c.name,c.surName,c.age)) " +
            "from Order o inner join o.customer c where o.paymentType is null")
    List<GetOrderListResponse> findPaymentTypeIsNull();

    @Query("select new com.tobeto.spring.b.services.dtos.responses.order.GetOrderListResponse(o.totalPrice,o.startDate," +
            "new com.tobeto.spring.b.services.dtos.responses.customer.GetListCustomerResponse(c.name,c.surName,c.age)) " +
            "from Order o inner join o.customer c where c.surName= :surName or c.name like %:name%")
    List<GetOrderListResponse> findByNameOrSurname(String name, String surName);
}
