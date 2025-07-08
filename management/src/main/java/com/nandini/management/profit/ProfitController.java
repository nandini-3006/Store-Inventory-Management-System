package com.nandini.management.profit;

import com.nandini.management.products.DamagedListDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@RestController
@RequestMapping("/profit ")
public class ProfitController {
private final ProfitService profitService;
public ProfitController(ProfitService profitService){
    this.profitService=profitService;
}
    @GetMapping("/daily_profit")
    public ResponseEntity<List<DailyProfit>> daily_profit(@RequestParam LocalDate date) {
        try {
            DailyProfit profit = profitService.daily_profit(date)
                    .orElseThrow();
            return ResponseEntity.ok(List.of(profit));
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
        @GetMapping("/monthly_profit")
        public ResponseEntity<List<MonthlyProfit>> monthly_profit(@RequestParam int month,@RequestParam Long year) {
            try {
                MonthlyProfit profit = profitService.monthly_profit(month,year)
                        .orElseThrow();
                return ResponseEntity.ok(List.of(profit));
            } catch (Exception e) {
                return ResponseEntity.badRequest().build();
            }
    }
}
