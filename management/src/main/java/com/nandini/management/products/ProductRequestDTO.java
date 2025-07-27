package com.nandini.management.products;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
public class ProductRequestDTO {
    private Double price;
    private Long quantity;
    private String brand;
    private String name;
    private LocalDate expiry;
    private Double profit;
    private Double discount;
    private String discount_status;
    private Boolean discount_bool;
    private Long threshold;
}