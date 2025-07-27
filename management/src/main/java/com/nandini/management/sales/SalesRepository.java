package com.nandini.management.sales;



import com.nandini.management.products.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import java.util.Optional;

@Repository
public interface SalesRepository  extends JpaRepository<SalesEntity,Long> {
    Optional<SalesEntity> findByNameAndBrandAndMonthAndYearAndDiscount_bool(String name, String brand, Integer month,Long year,Double discount,Boolean discount_bool);
    Optional<SalesEntity> findByMonthAndYear(Integer month,Long year);
    Optional<SalesEntity> findByName(String name);
    Optional<SalesEntity> findByBrand(String brand);
    @Query("SELECT s.name FROM SalesEntity s WHERE s.month=:month GROUP BY s.name ORDER BY SUM(s.quantity) DESC")
    List<String> findTopProducts(@Param("month") Integer month);
    @Query("SELECT s.brand FROM SalesEntity s WHERE s.month=:month AND s.name=:name GROUP BY s.brand ORDER BY SUM(s.quantity) DESC")
    List<String> findTopBrands(@Param("month") Integer month,@Param("name") String name);
}