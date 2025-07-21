package com.nandini.management.loss;

import com.nandini.management.profit.MonthlyProfit;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public class LossService {
    private final LossRepository lossRepository;
    public LossService(LossRepository lossRepository){
        this.lossRepository=lossRepository;
    }
    public List<LossEntity> daily_loss(LocalDate date){
        return lossRepository.findByDate(date);
    }
    public List<LossEntity> monthly_loss(int month, int year){
        return lossRepository.findByMonthAndYear(month,year);
    }
}
