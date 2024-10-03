package com.hhpluslecture.repository.port;

import com.hhpluslecture.repository.domain.LectureRegistration;

import java.util.List;

public interface LectureRegistrationRepository {
    LectureRegistration create(LectureRegistration lectureRegistration);

    LectureRegistration findByMemberIdAndLectureItemId(Long memberId, Long lectureItemId);

    List<LectureRegistration> findAllByMemberId(Long memberId);
}
