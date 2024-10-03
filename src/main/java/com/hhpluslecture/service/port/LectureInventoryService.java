package com.hhpluslecture.service.port;

import com.hhpluslecture.repository.domain.LectureInventory;

import java.util.List;

public interface LectureInventoryService {
    LectureInventory checkAvailability(Long lectureItemId);

    void decreaseInventory(LectureInventory lectureInventory);

    List<LectureInventory> loadAllByLectureItemIds(List<Long> lectureItemIds);

    List<LectureInventory> loadAllAvailableByLectureItemIdIn(List<Long> lectureItemIds);
}
