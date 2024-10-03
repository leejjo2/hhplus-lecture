package com.hhpluslecture.repository;

import com.hhpluslecture.repository.domain.LectureRegistration;
import com.hhpluslecture.repository.orm.LectureRegistrationJpaRepository;
import com.hhpluslecture.repository.port.LectureRegistrationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class LectureRegistrationRepositoryImpl implements LectureRegistrationRepository {
    private final LectureRegistrationJpaRepository lectureRegistrationJpaRepository;

    @Override
    public LectureRegistration create(LectureRegistration lectureRegistration) {
        return LectureRegistration.fromEntity(lectureRegistrationJpaRepository.save(LectureRegistration.toEntity(lectureRegistration)));
    }

    @Override
    public LectureRegistration findByMemberIdAndLectureItemId(Long memberId, Long lectureItemId) {
        return LectureRegistration.fromEntity(lectureRegistrationJpaRepository.findByMemberIdAndLectureItemId(memberId, lectureItemId).orElse(null));
    }

    @Override
    public List<LectureRegistration> findAllByMemberId(Long memberId) {
        return lectureRegistrationJpaRepository.findAllByMemberId(memberId).stream().map(LectureRegistration::fromEntity).toList();
    }
}
