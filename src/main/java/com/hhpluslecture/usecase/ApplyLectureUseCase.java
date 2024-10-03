package com.hhpluslecture.usecase;

import com.hhpluslecture.repository.domain.LectureInventory;
import com.hhpluslecture.repository.domain.LectureRegistration;
import com.hhpluslecture.service.port.LectureInventoryService;
import com.hhpluslecture.service.port.LectureRegistrationService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
public class ApplyLectureUseCase {

    private final LectureInventoryService lectureInventoryService;
    private final LectureRegistrationService lectureRegistrationService;

    @Getter
    @AllArgsConstructor
    public static class Input {
        Long memberId;
        Long lectureItemId;
    }

    @AllArgsConstructor
    @Getter
    public static class Output {
        Long lectureRegistrationId;
        Long memberId;
        Long lectureItemId;
        LocalDateTime registrationDate;
    }
    @Transactional
    public Output execute(Input input) {
        //
        Long memberId = input.getMemberId();
        Long lectureItemId = input.getLectureItemId();

        LectureInventory lectureInventory = lectureInventoryService.checkAvailability(lectureItemId);
        LectureRegistration registeredLectureRegistration = lectureRegistrationService.register(memberId, lectureItemId);
        lectureInventoryService.decreaseInventory(lectureInventory);

        return new Output(
                registeredLectureRegistration.getId(),
                registeredLectureRegistration.getMemberId(),
                registeredLectureRegistration.getLectureItemId(),
                registeredLectureRegistration.getRegistrationDate()
        );
    }


}
