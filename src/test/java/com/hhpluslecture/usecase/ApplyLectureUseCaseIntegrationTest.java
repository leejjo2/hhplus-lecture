package com.hhpluslecture.usecase;

import com.hhpluslecture.exception.BusinessException;
import com.hhpluslecture.exception.LectureErrorCode;
import com.hhpluslecture.repository.domain.entity.LectureEntity;
import com.hhpluslecture.repository.domain.entity.LectureInventoryEntity;
import com.hhpluslecture.repository.domain.entity.LectureItemEntity;
import com.hhpluslecture.repository.domain.entity.LectureRegistrationEntity;
import com.hhpluslecture.repository.orm.LectureInventoryJpaRepository;
import com.hhpluslecture.repository.orm.LectureItemJpaRepository;
import com.hhpluslecture.repository.orm.LectureJpaRepository;
import com.hhpluslecture.repository.orm.LectureRegistrationJpaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@DisplayName("ApplyLectureUseCase 통합 테스트")
class ApplyLectureUseCaseIntegrationTest {

    @Autowired
    private ApplyLectureUseCase target;


    @Autowired
    private LectureJpaRepository lectureJpaRepository;

    @Autowired
    private LectureItemJpaRepository lectureItemJpaRepository;

    @Autowired
    private LectureInventoryJpaRepository lectureInventoryJpaRepository;

    @Autowired
    private LectureRegistrationJpaRepository lectureRegistrationJpaRepository;


    @BeforeEach
    void setUp() {
        lectureJpaRepository.deleteAll();
        lectureItemJpaRepository.deleteAll();
        lectureInventoryJpaRepository.deleteAll();
        lectureRegistrationJpaRepository.deleteAll();
    }

    @Test
    @DisplayName("수강 신청 실패 - 존재하지 않는 강의")
    void shouldThrowBusinessExceptionWhenLectureNotFound() {
        // given
        Long memberId = 1L;
        Long lectureItemId = 1L;

        // when
        BusinessException result = assertThrows(BusinessException.class, () -> {
            target.execute(new ApplyLectureUseCase.Input(memberId, lectureItemId));
        });

        // then
        assertThat(result.getMessage()).isEqualTo(LectureErrorCode.LECTURE_NOT_FOUND.getMessage());
    }

    @Test
    @DisplayName("수강 신청 실패 - 수강 신청 인원 초과")
    void shouldThrowBusinessExceptionWhenExceedCapacity() {
        // given
        Long memberId = 1L;
        int capacity = 30;
        int availableSeats = 0;

        LectureEntity lectureEntity = lectureJpaRepository.save(
                new LectureEntity(null, "name", "gangsa")
        );
        LectureItemEntity lectureItemEntity = lectureItemJpaRepository.save(
                new LectureItemEntity(null, lectureEntity.getId(), LocalDate.now(), capacity)
        );
        LectureInventoryEntity lectureInventoryEntity = lectureInventoryJpaRepository.save(
                new LectureInventoryEntity(null, lectureItemEntity.getId(), availableSeats)
        );
        Long lectureId = lectureEntity.getId();
        Long lectureItemEntityId = lectureItemEntity.getId();

        // when
        BusinessException result = assertThrows(BusinessException.class, () -> {
            target.execute(new ApplyLectureUseCase.Input(memberId, lectureItemEntityId));
        });

        // then
        assertThat(result.getMessage()).isEqualTo(LectureErrorCode.ENROLLMENT_EXCEED_CAPACITY
                .getMessage());
    }

    @Test
    @DisplayName("수강 신청 성공")
    void shouldSuccessApplyLecture() {
        // given
        Long memberId = 1L;
        int capacity = 30;
        int availableSeats = 30;
        LectureEntity lectureEntity = lectureJpaRepository.save(
                new LectureEntity(null, "name", "gangsa")
        );
        LectureItemEntity lectureItemEntity = lectureItemJpaRepository.save(
                new LectureItemEntity(null, lectureEntity.getId(), LocalDate.now(), capacity)
        );
        LectureInventoryEntity lectureInventoryEntity = lectureInventoryJpaRepository.save(
                new LectureInventoryEntity(null, lectureItemEntity.getId(), availableSeats)
        );
        Long lectureId = lectureEntity.getId();
        Long lectureItemEntityId = lectureItemEntity.getId();

        // when
        ApplyLectureUseCase.Output output = target.execute(new ApplyLectureUseCase.Input(memberId, lectureItemEntityId));

        // then
        assertThat(output.getMemberId()).isEqualTo(memberId);
        assertThat(output.getLectureItemId()).isEqualTo(lectureItemEntityId);
        assertThat(output.getRegistrationDate()).isNotNull();

        LectureInventoryEntity updatedLectureInventoryEntity = lectureInventoryJpaRepository
                .findByLectureItemId(lectureItemEntityId).get();
        assertThat(updatedLectureInventoryEntity.getAvailableSeats()).isEqualTo(
                availableSeats - 1);
    }

}
