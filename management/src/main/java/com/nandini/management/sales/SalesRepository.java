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
    Optional<SalesEntity> findByNameAndBrandAndMonthAndYear(String name, String brand, Integer month,Long year);
    @Query("SELECT s.brand FROM SalesEntity s WHERE s.name=:name GROUP BY s.brand ORDER BY SUM(s.quantity) DESC")
    List<String> findTopBrands(@Param("name") String name);
}