package com.hhpluslecture.service;

import com.hhpluslecture.exception.BusinessException;
import com.hhpluslecture.exception.LectureErrorCode;
import com.hhpluslecture.repository.domain.LectureInventory;
import com.hhpluslecture.repository.port.LectureInventoryRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DisplayName("LectureInventoryService 단위 테스트")
class LectureInventoryServiceTest {

    @Test
    @DisplayName("checkAvailability 테스트 성공")
    void shouldSuccessCheckAvailability() {

        Long lectureInventoryId = 1L;
        Long lectureItemId = 1L;
        int availableSeats = 30;
        // given
        LectureInventoryServiceImpl lectureInventoryService = new LectureInventoryServiceImpl(
                new LectureInventoryRepository() {
                    @Override
                    public LectureInventory findLectureInventoryByIdForUpdate(Long lectureItemId) {
                        return new LectureInventory(lectureInventoryId, lectureItemId, availableSeats);
                    }

                    @Override
                    public void update(LectureInventory lectureInventory) {

                    }

                    @Override
                    public List<LectureInventory> findAllByLectureItemIds(Set<Long> lectureItemIds) {
                        return null;
                    }

                    @Override
                    public List<LectureInventory> findAllAvailableByLectureItemIdIn(Set<Long> lectureItemIds) {
                        return null;
                    }
                }
        );

        // when
        LectureInventory lectureInventory = lectureInventoryService.checkAvailability(lectureItemId);

        // then
        assertThat(lectureInventory.getId()).isEqualTo(lectureInventoryId);
        assertThat(lectureInventory.getLectureItemId()).isEqualTo(lectureItemId);
        assertThat(lectureInventory.getAvailableSeats()).isEqualTo(availableSeats);
    }


    @Test
    @DisplayName("checkAvailability 테스트 - 정원초과")
    void shouldThrowExceptionWhenExceedCapacity() {

        Long lectureInventoryId = 1L;
        Long lectureItemId = 1L;
        int availableSeats = 0;
        // given
        LectureInventoryServiceImpl lectureInventoryService = new LectureInventoryServiceImpl(
                new LectureInventoryRepository() {
                    @Override
                    public LectureInventory findLectureInventoryByIdForUpdate(Long lectureItemId) {
                        return new LectureInventory(lectureInventoryId, lectureItemId, availableSeats);
                    }

                    @Override
                    public void update(LectureInventory lectureInventory) {

                    }

                    @Override
                    public List<LectureInventory> findAllByLectureItemIds(Set<Long> lectureItemIds) {
                        return null;
                    }

                    @Override
                    public List<LectureInventory> findAllAvailableByLectureItemIdIn(Set<Long> lectureItemIds) {
                        return null;
                    }
                }
        );

        // when
        BusinessException businessException = assertThrows(BusinessException.class, () -> lectureInventoryService.checkAvailability(lectureItemId));

        // then
        assertThat(businessException.getMessage()).isEqualTo(LectureErrorCode.ENROLLMENT_EXCEED_CAPACITY.getMessage());
    }
}