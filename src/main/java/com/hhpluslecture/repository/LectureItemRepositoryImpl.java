package com.hhpluslecture.repository;

import com.hhpluslecture.repository.domain.LectureItem;
import com.hhpluslecture.repository.orm.LectureItemJpaRepository;
import com.hhpluslecture.repository.port.LectureItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class LectureItemRepositoryImpl implements LectureItemRepository {
    private final LectureItemJpaRepository lectureItemJpaRepository;

    @Override
    public List<LectureItem> findAllByIds(Set<Long> lectureItemIds) {
        return lectureItemJpaRepository.findAllByIdIn(lectureItemIds).stream().map(LectureItem::fromEntity).collect(Collectors.toList());
    }

    @Override
    public List<LectureItem> findAllByDateRange(LocalDate startDate, LocalDate endDate) {
        return lectureItemJpaRepository.findAllByDateRange(startDate, endDate).stream().map(LectureItem::fromEntity).collect(Collectors.toList());
    }

}
