package com.hhpluslecture.repository.orm;

import com.hhpluslecture.repository.domain.entity.LectureInventoryEntity;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface LectureInventoryJpaRepository extends JpaRepository<LectureInventoryEntity, String> {
    List<LectureInventoryEntity> findAll();


    List<LectureInventoryEntity> findAllByLectureItemIdIn(List<Long> lectureItemIds);

    @Query("SELECT li FROM LectureInventoryEntity li WHERE li.lectureItemId IN :lectureItemIds AND li.availableSeats > 0")
    List<LectureInventoryEntity> findAllAvailableByLectureItemIdIn(List<Long> lectureItemIds);

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("select d from LectureInventoryEntity d where d.lectureItemId = :lectureItemId")
    Optional<LectureInventoryEntity> findByLectureItemIdForUpdate(Long lectureItemId);
}
