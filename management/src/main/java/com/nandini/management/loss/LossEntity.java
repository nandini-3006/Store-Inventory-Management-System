package com.nandini.management.loss;

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
@Table(name = "loss")
public class LossEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;

    @Column(nullable = false)
    private Long quantity;

    @Column(nullable = true)
    private String brand;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Double loss;

    @Column(nullable = false)
    private LocalDate date;

    @Column(nullable = false)
    private String status;



}
