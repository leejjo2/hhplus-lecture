package com.hhpluslecture.repository.port;

import com.hhpluslecture.repository.domain.LectureInventory;

import java.util.List;

public interface LectureInventoryRepository {
    LectureInventory findLectureInventoryByIdForUpdate(Long lectureItemId);

    void update(LectureInventory lectureInventory);

    List<LectureInventory> findAllByLectureItemIds(List<Long> lectureItemIds);

    List<LectureInventory> findAllAvailableByLectureItemIdIn(List<Long> lectureItemIds);
}
