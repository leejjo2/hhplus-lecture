package com.hhpluslecture.controller;

import com.hhpluslecture.usecase.ApplyLectureUseCase;
import com.hhpluslecture.usecase.LoadAvailableLectureItemsUseCase;
import com.hhpluslecture.usecase.LoadMemberRegistrationUseCase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@Slf4j
@RequestMapping("/lectures")
@RestController
@RequiredArgsConstructor
public class LectureController {

    private final ApplyLectureUseCase applyLectureUseCase;
    private final LoadMemberRegistrationUseCase loadMemberRegistrationUseCase;
    private final LoadAvailableLectureItemsUseCase loadAvailableLectureItemsUseCase;

    /**
     * 특강 신청 API
     *
     * @param input (memberId, lectureId, lectureItemId)
     * @return output (lectureRegistrationId)
     */
    @PostMapping("/apply")
    public ResponseEntity<ApplyLectureUseCase.Output> lectureApply(
            @RequestBody ApplyLectureUseCase.Input input
    ) {
        //
        return ResponseEntity.ok(this.applyLectureUseCase.execute(input));
    }

    /**
     * 신청 가능한 특강 목록 조회 API
     *
     * @return List<LoadAvailableLectureItemsUseCase.Output>
     */
    @GetMapping("/available")
    public ResponseEntity<List<LoadAvailableLectureItemsUseCase.Output>> getAvailableLectures(
            @RequestParam("startDate") String startDate,
            @RequestParam("endDate") String endDate
    ) {
        LocalDate startLocalDate = LocalDate.parse(startDate);
        LocalDate endlocalDate = LocalDate.parse(endDate);
        return ResponseEntity.ok(loadAvailableLectureItemsUseCase.execute(startLocalDate, endlocalDate));
    }

    /**
     * 특강 신청 완료 목록 조회 API
     *
     * @return List<LoadMemberRegistrationUseCase.Output>
     */
    @GetMapping("/registration/{memberId}")
    public ResponseEntity<List<LoadMemberRegistrationUseCase.Output>> loadLectures(
            @PathVariable Long memberId
    ) {
        //
        return ResponseEntity.ok(loadMemberRegistrationUseCase.execute(memberId));
    }
}
