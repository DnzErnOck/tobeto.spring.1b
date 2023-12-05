package com.tobeto.spring.b.repositories;

import com.tobeto.spring.b.entities.Bill;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface BillRepository extends JpaRepository<Bill,Integer> {
    List<Bill> findByBillDateAfter(LocalDate billDate);
    Bill findFirstByTotalPriceLessThanEqualOrderByTotalPriceDesc(Double totalPrice);
}
