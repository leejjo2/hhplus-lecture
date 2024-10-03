package com.hhpluslecture.repository;

import com.hhpluslecture.exception.BusinessException;
import com.hhpluslecture.exception.LectureErrorCode;
import com.hhpluslecture.repository.domain.LectureInventory;
import com.hhpluslecture.repository.orm.LectureInventoryJpaRepository;
import com.hhpluslecture.repository.port.LectureInventoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class LectureInventoryRepositoryImpl implements LectureInventoryRepository {
    private final LectureInventoryJpaRepository lectureInventoryJpaRepository;


    @Override
    public LectureInventory findLectureInventoryByIdForUpdate(Long lectureItemId) {
        return lectureInventoryJpaRepository.findByLectureItemIdForUpdate(lectureItemId).map(LectureInventory::fromEntity).orElseThrow(() -> new BusinessException(LectureErrorCode.LECTURE_NOT_FOUND));
    }

    @Override
    public void update(LectureInventory lectureInventory) {
        lectureInventoryJpaRepository.save(LectureInventory.toEntity(lectureInventory));
    }

    @Override
    public List<LectureInventory> findAllByLectureItemIds(Set<Long> lectureItemIds) {
        return lectureInventoryJpaRepository.findAllByLectureItemIdIn(lectureItemIds).stream().map(LectureInventory::fromEntity).collect(Collectors.toList());
    }
    @Override
    public List<LectureInventory> findAllAvailableByLectureItemIdIn(Set<Long> lectureItemIds) {
        return lectureInventoryJpaRepository.findAllAvailableByLectureItemIdIn(lectureItemIds).stream().map(LectureInventory::fromEntity).collect(Collectors.toList());
    }
}
