package com.nandini.management.products;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "products")
public class ProductEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;
    //different class for suppliers,we will manage it
    @Column(nullable = false)
    private Double price;

    @Column(nullable = false)
    private Long quantity;

    @Column(nullable = true)
    private String brand;

    @Column(nullable = false)
    private String name;

    @Column(nullable = true)
    private LocalDate expiry;

    @Column(nullable = false)
    private Double profit;

    @Column(nullable = true)
    private Double discount;

    @Column(nullable = true)
    private String discount_status;

    @Column(nullable = true)
    private Boolean discount_bool;

    @Column(nullable = false)
    private Long threshold;
}