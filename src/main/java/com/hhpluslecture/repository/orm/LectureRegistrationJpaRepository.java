package com.hhpluslecture.repository.orm;

import com.hhpluslecture.repository.domain.entity.LectureRegistrationEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface LectureRegistrationJpaRepository extends JpaRepository<LectureRegistrationEntity, String> {
    List<LectureRegistrationEntity> findAll();

    Optional<LectureRegistrationEntity> findByMemberIdAndLectureItemId(Long memberId, Long lectureItemId);

    List<LectureRegistrationEntity> findAllByMemberId(Long memberId);
}
