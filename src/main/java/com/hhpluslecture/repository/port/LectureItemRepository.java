package com.hhpluslecture.repository.port;

import com.hhpluslecture.repository.domain.LectureItem;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

public interface LectureItemRepository {

    List<LectureItem> findAllByIds(Set<Long> lectureItemIds);

    List<LectureItem> findAllByDateRange(LocalDate startDate, LocalDate endDate);
}
