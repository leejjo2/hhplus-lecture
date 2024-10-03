package com.hhpluslecture.repository.domain;

import com.hhpluslecture.repository.domain.entity.LectureItemEntity;
import lombok.*;

import java.time.LocalDate;


@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LectureItem {

    private Long id;

    private Long lectureId;

    private LocalDate date;

    private int capacity;

    // 팩토리 메서드
    public static LectureItem fromEntity(LectureItemEntity entity) {
        if (entity == null) {
            return null;
        }
        return new LectureItem(
                entity.getId(),
                entity.getLectureId(),
                entity.getDate(),
                entity.getCapacity()
        );
    }

    public static LectureItemEntity toEntity(LectureItem domain) {
        return new LectureItemEntity(
                domain.getId(),
                domain.getLectureId(),
                domain.getDate(),
                domain.getCapacity()
        );
    }
}
