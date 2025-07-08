package com.nandini.management.products;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
public class DamagedDTO {
    private String name;
    private String brand;
    private LocalDate expiry;
    private Long quantity;
}