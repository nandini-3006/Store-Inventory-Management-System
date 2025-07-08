package com.nandini.management.profit;

import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.time.Month;
import java.util.Optional;

public interface MonthlyProfitRepository  extends JpaRepository<MonthlyProfit,Long> {
    Optional<MonthlyProfit> findByMonthAndYear(Integer month,Long year);
}
