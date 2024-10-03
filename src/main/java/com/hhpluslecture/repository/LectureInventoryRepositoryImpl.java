package com.hhpluslecture.repository;

import com.hhpluslecture.repository.domain.LectureInventory;
import com.hhpluslecture.repository.orm.LectureInventoryJpaRepository;
import com.hhpluslecture.repository.port.LectureInventoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class LectureInventoryRepositoryImpl implements LectureInventoryRepository {
    private final LectureInventoryJpaRepository lectureInventoryJpaRepository;


    @Override
    public LectureInventory findLectureInventoryByIdForUpdate(Long lectureItemId) {
        return LectureInventory.fromEntity(lectureInventoryJpaRepository.findByLectureItemIdForUpdate(lectureItemId).orElseThrow());
    }

    @Override
    public void update(LectureInventory lectureInventory) {
        lectureInventoryJpaRepository.save(LectureInventory.toEntity(lectureInventory));
    }

    @Override
    public List<LectureInventory> findAllByLectureItemIds(List<Long> lectureItemIds) {
        return lectureInventoryJpaRepository.findAllByLectureItemIdIn(lectureItemIds).stream().map(LectureInventory::fromEntity).collect(Collectors.toList());
    }
}
