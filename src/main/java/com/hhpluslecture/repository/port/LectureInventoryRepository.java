package com.hhpluslecture.repository.port;

import com.hhpluslecture.repository.domain.LectureInventory;

import java.util.List;
import java.util.Set;

public interface LectureInventoryRepository {
    LectureInventory findLectureInventoryByIdForUpdate(Long lectureItemId);

    void update(LectureInventory lectureInventory);


    List<LectureInventory> findAllByLectureItemIds(Set<Long> lectureItemIds);

    List<LectureInventory> findAllAvailableByLectureItemIdIn(Set<Long> lectureItemIds);
}
