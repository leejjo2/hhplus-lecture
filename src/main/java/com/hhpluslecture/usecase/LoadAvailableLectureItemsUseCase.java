package com.hhpluslecture.usecase;

import com.hhpluslecture.repository.domain.Lecture;
import com.hhpluslecture.repository.domain.LectureInventory;
import com.hhpluslecture.repository.domain.LectureItem;
import com.hhpluslecture.service.port.LectureInventoryService;
import com.hhpluslecture.service.port.LectureItemService;
import com.hhpluslecture.service.port.LectureService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class LoadAvailableLectureItemsUseCase {

    private final LectureInventoryService lectureInventoryService;
    private final LectureService lectureService;
    private final LectureItemService lectureItemService;

    @AllArgsConstructor
    @Getter
    public static class Output {
        Long lectureId;
        String lectureName;
        String gangsa;
        Long lectureItemId;
        LocalDate lectureItemDate;
        int capacity;
        Long lectureInventoryId;
        int availableSeats;
    }

    @Transactional
    public List<Output> execute(LocalDate startLocalDate, LocalDate endLocalDate) {
        //
        List<LectureItem> lectureItems = lectureItemService.loadAllByDateRange(startLocalDate, endLocalDate);

        List<Long> lectureIds = lectureItems.stream().map(LectureItem::getLectureId).collect(Collectors.toList());
        List<Long> lectureItemIds = lectureItems.stream().map(LectureItem::getId).collect(Collectors.toList());

        Map<Long, Lecture> lectureMap = lectureService.loadAllByIds(lectureIds).stream().collect(Collectors.toMap(Lecture::getId, Function.identity()));
        Map<Long, LectureInventory> lectureInventoryMap = lectureInventoryService.loadAllByLectureItemIds(lectureItemIds).stream().collect(Collectors.toMap(LectureInventory::getId, Function.identity()));

        List<Output> outputList = new ArrayList<>();
        for (LectureItem lectureItem : lectureItems) {
            Lecture lecture = lectureMap.get(lectureItem.getLectureId());
            LectureInventory lectureInventory = lectureInventoryMap.get(lectureItem.getId());

            Output out = new Output(
                    lecture.getId(),
                    lecture.getName(),
                    lecture.getGangsa(),
                    lectureItem.getId(),
                    lectureItem.getDate(),
                    lectureItem.getCapacity(),
                    lectureInventory.getId(),
                    lectureInventory.getAvailableSeats()
            );

            outputList.add(out);
        }
        return outputList;
    }


}
