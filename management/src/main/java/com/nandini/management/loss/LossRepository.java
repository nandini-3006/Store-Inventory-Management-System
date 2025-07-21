package com.nandini.management.loss;

import com.nandini.management.products.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface LossRepository extends JpaRepository<LossEntity,Long> {
    List<LossEntity> findByDate(LocalDate Date);
    @Query("SELECT l FROM LossEntity l WHERE FUNCTION('MONTH', l.date) = :month AND FUNCTION('YEAR', l.date) = :year")
    List<LossEntity> findByMonthAndYear(@Param("month") int month, @Param("year") int year);
}
