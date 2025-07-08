package com.nandini.management.profit;

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
@Table(name = "monthlyprofit")
public class MonthlyProfit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;
    @Column(nullable = false)
    private Double profit;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Integer month;
    @Column(nullable = false)
    private Long year;
}
