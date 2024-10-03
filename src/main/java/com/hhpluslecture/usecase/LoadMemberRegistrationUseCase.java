package com.hhpluslecture.usecase;

import com.hhpluslecture.repository.domain.Lecture;
import com.hhpluslecture.repository.domain.LectureInventory;
import com.hhpluslecture.repository.domain.LectureItem;
import com.hhpluslecture.repository.domain.LectureRegistration;
import com.hhpluslecture.service.port.LectureInventoryService;
import com.hhpluslecture.service.port.LectureItemService;
import com.hhpluslecture.service.port.LectureRegistrationService;
import com.hhpluslecture.service.port.LectureService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class LoadMemberRegistrationUseCase {

    private final LectureInventoryService lectureInventoryService;
    private final LectureRegistrationService lectureRegistrationService;
    private final LectureService lectureService;
    private final LectureItemService lectureItemService;

    @AllArgsConstructor
    @Getter
    public static class Output {
        Long lectureRegistrationId;
        LocalDateTime registrationDate;
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
    public List<Output> execute(Long memberId) {
        //
        List<LectureRegistration> lectureRegistrations = lectureRegistrationService.loadByMemberId(memberId);

        Set<Long> lectureItemIds = lectureRegistrations.stream().map(LectureRegistration::getLectureItemId).collect(Collectors.toSet());

        List<Long> lectureIds = lectureItemService.loadAllByIds(lectureItemIds).stream().map(LectureItem::getLectureId).collect(Collectors.toList());

        Map<Long, Lecture> lectureMap = lectureService.loadAllByIds(lectureIds).stream().collect(Collectors.toMap(Lecture::getId, Function.identity()));
        Map<Long, LectureItem> lectureItemMap = lectureItemService.loadAllByIds(lectureItemIds).stream().collect(Collectors.toMap(LectureItem::getId, Function.identity()));
        Map<Long, LectureInventory> lectureInventoryMap = lectureInventoryService.loadAllByLectureItemIds(lectureItemIds).stream().collect(Collectors.toMap(LectureInventory::getId, Function.identity()));

        List<Output> outputList = new ArrayList<>();
        for (LectureRegistration lectureRegistration : lectureRegistrations) {
            LectureItem lectureItem = lectureItemMap.get(lectureRegistration.getLectureItemId());
            Lecture lecture = lectureMap.get(lectureItem.getLectureId());
            LectureInventory lectureInventory = lectureInventoryMap.get(lectureRegistration.getLectureItemId());

            Output out = new Output(
                    lectureRegistration.getId(),
                    lectureRegistration.getRegistrationDate(),
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
