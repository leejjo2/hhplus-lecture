package com.hhpluslecture.service.port;

import com.hhpluslecture.repository.domain.LectureItem;

import java.time.LocalDate;
import java.util.List;

public interface LectureItemService {
    List<LectureItem> loadAllByIds(List<Long> lectureItemIds);

    List<LectureItem> loadAllByDateRange(LocalDate startDate, LocalDate endDate);
}
