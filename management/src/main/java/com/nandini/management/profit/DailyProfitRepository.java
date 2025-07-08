package com.nandini.management.profit;

import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.Optional;

public interface DailyProfitRepository extends JpaRepository<DailyProfit,Long> {
    Optional<DailyProfit> findByDate(LocalDate date);
}
