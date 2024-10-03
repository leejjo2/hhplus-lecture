package com.hhpluslecture.repository.orm;

import com.hhpluslecture.repository.domain.entity.LectureItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

public interface LectureItemJpaRepository extends JpaRepository<LectureItemEntity, String> {
    List<LectureItemEntity> findAllByIdIn(Set<Long> ids);

    @Query("SELECT l FROM LectureItemEntity l WHERE  l.date BETWEEN :startDate AND :endDate")
    List<LectureItemEntity> findAllByDateRange(@Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);
}
