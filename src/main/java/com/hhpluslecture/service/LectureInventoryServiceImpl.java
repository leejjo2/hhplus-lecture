package com.hhpluslecture.service;

import com.hhpluslecture.exception.BusinessException;
import com.hhpluslecture.exception.LectureErrorCode;
import com.hhpluslecture.repository.domain.LectureInventory;
import com.hhpluslecture.repository.port.LectureInventoryRepository;
import com.hhpluslecture.service.port.LectureInventoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class LectureInventoryServiceImpl implements LectureInventoryService {

    private final LectureInventoryRepository lectureInventoryRepository;

    @Override
    public LectureInventory checkAvailability(Long lectureItemId) {
        LectureInventory lectureInventory = lectureInventoryRepository.findLectureInventoryByIdForUpdate(lectureItemId);
        if (lectureInventory.getAvailableSeats() == 0) {
            throw new BusinessException(LectureErrorCode.ENROLLMENT_EXCEED_CAPACITY);
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
    public List<LectureInventory> loadAllByLectureItemIds(Set<Long> lectureItemIds) {
        return lectureInventoryRepository.findAllByLectureItemIds(lectureItemIds);
    }
    @Override
    public List<LectureInventory> loadAllAvailableByLectureItemIdIn(Set<Long> lectureItemIds) {
        return lectureInventoryRepository.findAllAvailableByLectureItemIdIn(lectureItemIds);
    }
}
