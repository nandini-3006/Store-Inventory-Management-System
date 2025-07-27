package com.nandini.management.loss;

import com.nandini.management.profit.DailyProfit;
import com.nandini.management.profit.MonthlyProfit;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@RestController
@PreAuthorize("hasAnyRole('OWNER', 'MANAGER')")
@RequestMapping("/loss")
public class LossController {
    private final LossService lossService;
    public LossController(LossService lossService){
        this.lossService=lossService;
    }
    @GetMapping("/daily_loss")
    public ResponseEntity<List<LossEntity>> daily_loss(@RequestParam LocalDate date) {
        try {
            List<LossEntity> loss = lossService.daily_loss(date);
            return ResponseEntity.ok(loss);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
        @GetMapping("/monthly_loss")
        public ResponseEntity<List<LossEntity>> monthly_loss(@RequestParam int month,@RequestParam int year){
            try {
                List<LossEntity> loss = lossService.monthly_loss(month, year);
                return ResponseEntity.ok(loss);
            } catch (Exception e) {
                return ResponseEntity.badRequest().build();
            }
        }
}
