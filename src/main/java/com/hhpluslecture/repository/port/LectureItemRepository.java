package com.hhpluslecture.repository.port;

import com.hhpluslecture.repository.domain.LectureItem;

import java.time.LocalDate;
import java.util.List;

public interface LectureItemRepository {
    List<LectureItem> findAllByIds(List<Long> lectureItemIds);

    List<LectureItem> findAllByDateRange(LocalDate startDate, LocalDate endDate);
}
