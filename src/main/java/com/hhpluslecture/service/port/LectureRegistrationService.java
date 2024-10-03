package com.hhpluslecture.service.port;

import com.hhpluslecture.repository.domain.LectureRegistration;

import java.util.List;

public interface LectureRegistrationService {

    LectureRegistration register(Long memberId, Long lectureId, Long lectureItemId);

    List<LectureRegistration> loadByMemberId(Long memberId);
}
