package com.nandini.management.products;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity,Long> {
    Optional<ProductEntity> findFirstByNameAndBrandOrderByExpiryAsc(String name, String brand);
    Optional<ProductEntity> findByNameAndBrandAndExpiry(String name, String brand,LocalDate Expiry);
    @Query("SELECT s.name, s.brand, MIN(s.threshold), SUM(s.quantity) FROM ProductEntity s GROUP BY s.name, s.brand HAVING SUM(s.quantity) < MIN(s.threshold)")
    List<LowStockDTO> findLowStock();
}
//jpa repository methods very very useful need to learn What field(s) to search? → findByNameAndBrand
//Any conditions? → GreaterThan, LessThan, Before, After, etc.
//Sorting? → OrderByFieldAsc/Desc
//Limit? → findFirstBy, findTop3By