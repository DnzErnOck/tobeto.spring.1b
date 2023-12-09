package com.tobeto.spring.b.repositories;

import com.tobeto.spring.b.entities.Bill;
import com.tobeto.spring.b.services.dtos.responses.bill.GetBillListResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface BillRepository extends JpaRepository<Bill,Integer> {
    List<Bill> findByBillDateAfter(LocalDate billDate);
    Bill findFirstByTotalPriceLessThanEqualOrderByTotalPriceDesc(Double totalPrice);

    boolean existsByTotalPrice(Double totalPrice);

    @Query("select new com.tobeto.spring.b.services.dtos.responses.bill.GetBillListResponse(" +
           "b.totalPrice,b.billDate,new com.tobeto.spring.b.services.dtos.responses.customer.GetListCustomerResponse(c.name,c.surName,c.age))" +
            " from Bill b inner join b.customer c")
    List<GetBillListResponse> findAllBill();

    @Query("select new com.tobeto.spring.b.services.dtos.responses.bill.GetBillListResponse(" +
           "b.totalPrice,b.billDate,new com.tobeto.spring.b.services.dtos.responses.customer.GetListCustomerResponse(c.name,c.surName,c.age))" +
            " from Bill b inner join b.customer c where b.totalPrice <= :totalPrice order by b.totalPrice desc")
    List<GetBillListResponse> findByTotalPriceLessThanEqualOrderByTotalPriceDesc(Double totalPrice);
}
