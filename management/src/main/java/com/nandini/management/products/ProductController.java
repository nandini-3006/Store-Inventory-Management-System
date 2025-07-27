package com.nandini.management.products;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;

@RestController
@PreAuthorize("hasAnyRole('OWNER','INVENTOR')")
@RequestMapping("/product")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {

        this.productService = productService;
    }
    @PostMapping("/stock-in")
    public ResponseEntity<ProductEntity> stock_in(@RequestBody ProductRequestDTO body) {
        try {
            var product = productService.addProduct(body.getPrice(), body.getQuantity(), body.getBrand(),body.getName(),body.getExpiry(),body.getProfit(),body.getDiscount(),body.getThreshold());
            return ResponseEntity.ok(product);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
    @PreAuthorize("hasAnyRole('OWNER','INVENTOR','CASHIER')")
    @PostMapping("/billing")
    public ResponseEntity<String> billing(@RequestBody BillingListDTO body) {
        try {
            productService.billing(body.getList());
            return ResponseEntity.ok("Billing Successful");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }
    }
    @PostMapping("/damaged")
    public ResponseEntity<String> damaged(@RequestBody DamagedListDTO body) {
        try {
            System.out.println("Received damaged request: " + body);
            productService.damaged(body.getList());
            return ResponseEntity.ok("Successful");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
        }
    }
    @GetMapping("/Low_stock")
    public ResponseEntity<List<LowStockDTO>> low_stock(){
        try {
            List<LowStockDTO> low_stock=productService.low_stock();
            return ResponseEntity.ok(low_stock);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).build();
        }
    }
}