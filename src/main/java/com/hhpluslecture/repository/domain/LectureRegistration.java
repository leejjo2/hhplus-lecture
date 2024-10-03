package com.hhpluslecture.repository.domain;

import com.hhpluslecture.repository.domain.entity.LectureRegistrationEntity;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LectureRegistration {

    private Long id;

    private Long memberId;

    private Long lectureId;

    private Long lectureItemId;

    private LocalDateTime registrationDate;

    // 팩토리 메서드
    public static LectureRegistration fromEntity(LectureRegistrationEntity entity) {
        if (entity == null) {
            return null;
        }
        return new LectureRegistration(
                entity.getId(),
                entity.getMemberId(),
                entity.getLectureId(),
                entity.getLectureItemId(),
                entity.getRegistrationDate()
        );
    }

    public static LectureRegistrationEntity toEntity(LectureRegistration domain) {
        return new LectureRegistrationEntity(
                domain.getId(),
                domain.getMemberId(),
                domain.getLectureId(),
                domain.getLectureItemId(),
                domain.getRegistrationDate()
        );
    }
}
