package com.nandini.management.sales;

import com.nandini.management.profit.MonthlyProfit;
import com.nandini.management.profit.ProfitService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@PreAuthorize("hasAnyRole('OWNER','MANAGER')")
@RequestMapping("/sales")
public class SalesController {
    private final SalesService salesService;
    public SalesController(SalesService salesService){
        this.salesService=salesService;
    }
    @GetMapping("/monthly_sales")
    public ResponseEntity<List<SalesEntity>> monthly_sales(@RequestParam int month, @RequestParam Long year) {
        try {
            SalesEntity sales = salesService.sales_data(month,year)
                    .orElseThrow();
            return ResponseEntity.ok(List.of(sales));
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
    @GetMapping("/sales_brand")
    public ResponseEntity<List<SalesEntity>> sales_brand(@RequestParam String brand) {
        try {
            SalesEntity sales = salesService.sales_brand_data(brand)
                    .orElseThrow();
            return ResponseEntity.ok(List.of(sales));
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
    @GetMapping("/sales_product")
    public ResponseEntity<List<SalesEntity>> sales_product(@RequestParam String name) {
        try {
            SalesEntity sales = salesService.sales_name_data(name)
                    .orElseThrow();
            return ResponseEntity.ok(List.of(sales));
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
    @GetMapping("/top_selling_brands")
    public ResponseEntity<List<String>> top_brands(@RequestParam Integer month,@RequestParam String name) {
        try {
            List sales = salesService.top_selling_brand(month,name);
            return ResponseEntity.ok(sales);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
    @GetMapping("/top_selling_products")
    public ResponseEntity<List<String>> top_products(@RequestParam Integer month) {
        try {
            List sales = salesService.top_selling_products(month);
            return ResponseEntity.ok(sales);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
}
