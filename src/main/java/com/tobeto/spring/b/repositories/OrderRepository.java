package com.tobeto.spring.b.repositories;

import com.tobeto.spring.b.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order,Integer> {
}
