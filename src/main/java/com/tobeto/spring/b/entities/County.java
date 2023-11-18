package com.tobeto.spring.b.entities;

import jakarta.persistence.*;

import java.util.List;

@Table(name = "counties")
@Entity
public class County {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "name")
    private String name;

    @ManyToOne
    @JoinColumn(name = "city_id")
    private City city;

    @OneToMany(mappedBy = "county")
    private List<Address> addresses;
}
