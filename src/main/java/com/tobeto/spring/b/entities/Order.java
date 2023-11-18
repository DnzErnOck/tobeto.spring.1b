package com.tobeto.spring.b.entities;

import jakarta.persistence.*;
import jdk.dynalink.linker.LinkerServices;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "order_date")
    private LocalDate orderDate;
    @Column(name = "start_date")
    private LocalDate startDate;
    @Column(name = "finish_date")
    private LocalDate finishDate;
    @Column(name = "total_price")
    private double totalPrice;
    @Column(name = "payment_type")
    private String paymentType;

    @OneToMany(mappedBy = "order")
    private List<Car> cars;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @OneToOne(mappedBy = "order",cascade = CascadeType.ALL)
    private Bill bill;

}
