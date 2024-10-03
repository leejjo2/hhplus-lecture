package com.hhpluslecture.service;

import com.hhpluslecture.exception.BusinessException;
import com.hhpluslecture.exception.LectureErrorCode;
import com.hhpluslecture.repository.domain.LectureRegistration;
import com.hhpluslecture.repository.port.LectureRegistrationRepository;
import com.hhpluslecture.service.port.LectureRegistrationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;

@Service
@RequiredArgsConstructor
public class LectureRegistrationServiceImpl implements LectureRegistrationService {
    private final LectureRegistrationRepository lectureRegistrationRepository;

    @Override
    public LectureRegistration register(Long memberId, Long lectureItemId) {
        //
        LectureRegistration checkLectureRegistration = lectureRegistrationRepository.findByMemberIdAndLectureItemId(memberId, lectureItemId);
        if (checkLectureRegistration != null) {
            throw new BusinessException(LectureErrorCode.ALREADY_REGISTERED_LECTURE);
        }

        LectureRegistration lectureRegistration = new LectureRegistration(null, memberId, lectureItemId, LocalDateTime.now(ZoneId.of("Asia/Seoul")));
        return lectureRegistrationRepository.create(lectureRegistration);
    }

    @Override
    public List<LectureRegistration> loadByMemberId(Long memberId) {
        return lectureRegistrationRepository.findAllByMemberId(memberId);
    }
}
