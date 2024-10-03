package com.hhpluslecture.service;

import com.hhpluslecture.repository.domain.LectureItem;
import com.hhpluslecture.repository.port.LectureItemRepository;
import com.hhpluslecture.service.port.LectureItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class LectureItemServiceImpl implements LectureItemService {
    private final LectureItemRepository lectureItemRepository;

    @Override
    public List<LectureItem> loadAllByIds(List<Long> lectureItemIds) {
        return lectureItemRepository.findAllByIds(lectureItemIds);
    }

    @Override
    public List<LectureItem> loadAllByDateRange(LocalDate startDate, LocalDate endDate) {
        return lectureItemRepository.findAllByDateRange(startDate, endDate);
    }

}
