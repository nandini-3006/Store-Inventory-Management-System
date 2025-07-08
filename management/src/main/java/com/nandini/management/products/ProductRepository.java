package com.nandini.management.products;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity,Long> {
    Optional<ProductEntity> findFirstByNameAndBrandOrderByExpiryAsc(String name, String brand);
    Optional<ProductEntity> findByNameAndBrandAndExpiry(String name, String brand,LocalDate Expiry);
}
//jpa repository methods very very useful need to learn What field(s) to search? → findByNameAndBrand
//Any conditions? → GreaterThan, LessThan, Before, After, etc.
//Sorting? → OrderByFieldAsc/Desc
//Limit? → findFirstBy, findTop3By