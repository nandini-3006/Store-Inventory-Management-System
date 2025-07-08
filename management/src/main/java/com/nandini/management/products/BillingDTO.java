package com.nandini.management.products;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class BillingDTO {
    private String Name;
    private String brand;
    private Long quantity;
}