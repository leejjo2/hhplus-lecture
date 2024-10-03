package com.hhpluslecture.service;

import com.hhpluslecture.exception.custom.SeatExceededException;
import com.hhpluslecture.repository.domain.LectureInventory;
import com.hhpluslecture.repository.port.LectureInventoryRepository;
import com.hhpluslecture.service.port.LectureInventoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LectureInventoryServiceImpl implements LectureInventoryService {

    private final LectureInventoryRepository lectureInventoryRepository;

    @Override
    public LectureInventory checkAvailability(Long lectureItemId) {
        LectureInventory lectureInventory = lectureInventoryRepository.findLectureInventoryByIdForUpdate(lectureItemId);
        if (lectureInventory.getAvailableSeats() == 0) {
            throw new SeatExceededException();
        } else {
            return lectureInventory;
        }
    }

    @Override
    public void decreaseInventory(LectureInventory lectureInventory) {
        lectureInventory.apply();
        lectureInventoryRepository.update(lectureInventory);
    }

    @Override
    public List<LectureInventory> loadAllByLectureItemIds(List<Long> lectureItemIds) {
        return lectureInventoryRepository.findAllByLectureItemIds(lectureItemIds);
    }
    @Override
    public List<LectureInventory> loadAllAvailableByLectureItemIdIn(List<Long> lectureItemIds) {
        return lectureInventoryRepository.findAllAvailableByLectureItemIdIn(lectureItemIds);
    }
}
