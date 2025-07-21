package com.nandini.management.sales;

import java.util.List;
import java.util.Optional;

public class SalesService {
    private final SalesRepository salesRepository;
        public SalesService(SalesRepository salesRepository){
        this.salesRepository=salesRepository;
        }
        public Optional<SalesEntity> sales_data(Integer month, Long year){
            return salesRepository.findByMonthAndYear(month,year);
        }
    public Optional<SalesEntity> sales_name_data(String name){
        return salesRepository.findByName(name);
    }
    public Optional<SalesEntity> sales_brand_data(String brand){
        return salesRepository.findByBrand(brand);
    }
    public List<String> top_selling_brand(Integer month,String name){
        return salesRepository.findTopBrands(month,name);
    }
    public List<String> top_selling_products(Integer month){
        return salesRepository.findTopProducts(month);
    }
}
