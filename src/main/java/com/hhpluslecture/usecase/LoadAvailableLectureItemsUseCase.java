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
import java.util.Set;
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
        Set<Long> lectureItemIds = lectureItems.stream().map(LectureItem::getId).collect(Collectors.toSet());

        Map<Long, LectureItem> lectureItemMap = lectureItems.stream().collect(Collectors.toMap(LectureItem::getId, Function.identity()));
        Map<Long, Lecture> lectureMap = lectureService.loadAllByIds(lectureIds).stream().collect(Collectors.toMap(Lecture::getId, Function.identity()));
        List<LectureInventory> availableLectureInventories = lectureInventoryService.loadAllAvailableByLectureItemIdIn(lectureItemIds);

        List<Output> outputList = new ArrayList<>();
        for (LectureInventory availableLectureInventory : availableLectureInventories) {
            LectureItem lectureItem = lectureItemMap.get(availableLectureInventory.getLectureItemId());
            Lecture lecture = lectureMap.get(lectureItem.getLectureId());

            Output out = new Output(
                    lecture.getId(),
                    lecture.getName(),
                    lecture.getGangsa(),
                    lectureItem.getId(),
                    lectureItem.getDate(),
                    lectureItem.getCapacity(),
                    availableLectureInventory.getId(),
                    availableLectureInventory.getAvailableSeats()
            );

            outputList.add(out);
        }
        return outputList;
    }


}
