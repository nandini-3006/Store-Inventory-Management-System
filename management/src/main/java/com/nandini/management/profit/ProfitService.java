package com.nandini.management.profit;

import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import java.util.Optional;

@Service
public class ProfitService {
    private final DailyProfitRepository dailyProfitRepository;
    private final MonthlyProfitRepository monthlyProfitRepository;
    public ProfitService(DailyProfitRepository dailyProfitRepository,MonthlyProfitRepository monthlyProfitRepository){
       this.dailyProfitRepository= dailyProfitRepository;
       this.monthlyProfitRepository=monthlyProfitRepository;
    }
    public Optional<DailyProfit> daily_profit(LocalDate date){
        return dailyProfitRepository.findByDate(date);
    }
    public Optional<MonthlyProfit> monthly_profit(Integer mon,Long year){
        return monthlyProfitRepository.findByMonAndYear(mon,year);
    }
}
