package com.example.farm.entity;


import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Entity
@Table(name = "product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "type")
    private String type;

    @Column(name = "company")
    private String company;

    @Column(name = "shortdescription")
    private String shortDescription;

    @Column(name = "longdescription")
    private String longDescription;

    @Column(name = "rate")
    private BigDecimal rate;
}

