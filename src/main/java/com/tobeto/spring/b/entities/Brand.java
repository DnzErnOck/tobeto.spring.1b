package com.tobeto.spring.b.entities;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name="brands")
public class Brand {
    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "brand") //attribute ismi
    private List<Car> cars;
}
