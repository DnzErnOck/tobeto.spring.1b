package com.tobeto.spring.b.repositories;

import com.tobeto.spring.b.entities.Bill;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BillRepository extends JpaRepository<Bill,Integer> {
}
