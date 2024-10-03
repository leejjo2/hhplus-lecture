package com.hhpluslecture.repository.domain;

import com.hhpluslecture.repository.domain.entity.LectureEntity;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Lecture {
    private Long id;
    private String name;
    private String gangsa;


    // 팩토리 메서드
    public static Lecture fromEntity(LectureEntity entity) {
        if (entity == null) {
            return null;
        }
        return new Lecture(
                entity.getId(),
                entity.getName(),
                entity.getGangsa()
        );
    }
}
