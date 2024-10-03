package com.hhpluslecture.service;

import com.hhpluslecture.exception.custom.AlreadyRegisteredLectureException;
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
    public LectureRegistration register(Long memberId, Long lectureId, Long lectureItemId) {
        //
        LectureRegistration checkLectureRegistration = lectureRegistrationRepository.findByMemberIdAndLectureItemId(memberId, lectureItemId);
        if (checkLectureRegistration != null) {
            throw new AlreadyRegisteredLectureException("이미 등록한 강의입니다.");
        }

        LectureRegistration lectureRegistration = new LectureRegistration(null, memberId, lectureId, lectureItemId, LocalDateTime.now(ZoneId.of("Asia/Seoul")));
        return lectureRegistrationRepository.create(lectureRegistration);
    }

    @Override
    public List<LectureRegistration> loadByMemberId(Long memberId) {
        return lectureRegistrationRepository.findAllByMemberId(memberId);
    }
}
