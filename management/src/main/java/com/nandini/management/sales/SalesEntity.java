package com.nandini.management.sales;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.Month;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "sales")
public class SalesEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;
    //different class for suppliers which will manage it
    @Column(nullable = false)
    private Double price;

    @Column(nullable = false)
    private Long quantity;

    @Column(nullable = true)
    private String brand;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Double total;

    @Column(nullable = false)
    private Integer month;

    @Column(nullable = false)
    private Long year;

    @Column(nullable = true)
    private Double discount;

    @Column(nullable = true)
    private Boolean discount_bool;
}