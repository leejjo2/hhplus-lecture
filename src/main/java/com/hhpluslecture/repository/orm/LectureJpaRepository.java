package com.hhpluslecture.repository.orm;

import com.hhpluslecture.repository.domain.entity.LectureEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface LectureJpaRepository extends JpaRepository<LectureEntity, String> {
    List<LectureEntity> findAll();

    Optional<LectureEntity> findById(Long lectureId);

    List<LectureEntity> findAllByIdIn(List<Long> lectureIds);
}
