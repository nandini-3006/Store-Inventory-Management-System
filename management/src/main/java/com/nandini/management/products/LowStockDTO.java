package com.nandini.management.products;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class LowStockDTO {
    private String name;
    private String brand;
    private Long threshold;
    private Long quantity;

    public LowStockDTO(String name, String brand, Long threshold, Long quantity) {
        this.name = name;
        this.brand = brand;
        this.threshold = threshold;
        this.quantity = quantity;
    }
}
