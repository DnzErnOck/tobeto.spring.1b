package com.tobeto.spring.b.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table(name = "cars")
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Car {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "model_year")
    private int modelYear;
    @Column(name = "model_name")
    private String modelName;
    @Column(name = "color")
    private String color;
    @Column(name = "fuel_type")
    private String fuelType;
    @Column(name = "shift_type")
    private String shiftType;
    @Column(name = "price")
    private double price;

    //tekil alan ManyToOne - join column => FK 'NIN OLDUĞU TABLO
    //coğul olan OneToMany
    @ManyToOne
    @JoinColumn(name = "brand_id")
    private Brand brand;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;

}
