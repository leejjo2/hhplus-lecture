package com.hhpluslecture.service.port;

import com.hhpluslecture.repository.domain.LectureItem;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

public interface LectureItemService {

    List<LectureItem> loadAllByIds(Set<Long> lectureItemIds);

    List<LectureItem> loadAllByDateRange(LocalDate startDate, LocalDate endDate);
}
