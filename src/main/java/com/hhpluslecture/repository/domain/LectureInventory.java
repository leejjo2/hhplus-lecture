package com.hhpluslecture.repository.domain;

import com.hhpluslecture.repository.domain.entity.LectureInventoryEntity;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LectureInventory {

    private Long id;

    private Long lectureId;

    private Long lectureItemId;

    private int availableSeats;


    // 팩토리 메서드
    public static LectureInventory fromEntity(LectureInventoryEntity entity) {
        if (entity == null) {
            return null;
        }
        return new LectureInventory(
                entity.getId(),
                entity.getLectureId(),
                entity.getLectureItemId(),
                entity.getAvailableSeats()
        );
    }

    public static LectureInventoryEntity toEntity(LectureInventory domain) {
        return new LectureInventoryEntity(
                domain.getId(),
                domain.getLectureId(),
                domain.getLectureItemId(),
                domain.getAvailableSeats()
        );
    }

    public void apply() {
        this.availableSeats--;
    }

}
