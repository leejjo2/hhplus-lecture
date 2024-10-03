package com.hhpluslecture.service.port;

import com.hhpluslecture.repository.domain.LectureInventory;

import java.util.List;
import java.util.Set;

public interface LectureInventoryService {
    LectureInventory checkAvailability(Long lectureItemId);

    void decreaseInventory(LectureInventory lectureInventory);


    List<LectureInventory> loadAllByLectureItemIds(Set<Long> lectureItemIds);

    List<LectureInventory> loadAllAvailableByLectureItemIdIn(Set<Long> lectureItemIds);
}
