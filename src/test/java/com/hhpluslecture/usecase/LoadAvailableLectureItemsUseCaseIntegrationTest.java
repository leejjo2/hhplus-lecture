package com.hhpluslecture.usecase;

import com.hhpluslecture.repository.domain.entity.LectureEntity;
import com.hhpluslecture.repository.domain.entity.LectureInventoryEntity;
import com.hhpluslecture.repository.domain.entity.LectureItemEntity;
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

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@DisplayName("LoadAvailableLectureItemsUseCase 통합 테스트")
class LoadAvailableLectureItemsUseCaseIntegrationTest {

    @Autowired
    private LoadAvailableLectureItemsUseCase target;


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
    @DisplayName("수강신청 가능 목록 조회 - 성공")
    void shouldSuccessGetAvailableLectures() {

        // given
        int capacity = 30;
        int availableSeats = 30;

        List<LectureEntity> lectureEntities = lectureJpaRepository.saveAll(
                List.of(
                        new LectureEntity(null, "name", "gangsa"),
                        new LectureEntity(null, "name2", "gangsa2")
                )
        );
        List<LectureItemEntity> lectureItemEntities = lectureItemJpaRepository.saveAll(
                List.of(
                        new LectureItemEntity(null, lectureEntities.get(0).getId(), LocalDate.now(), capacity),
                        new LectureItemEntity(null, lectureEntities.get(1).getId(), LocalDate.now().plusDays(3L), capacity)
                )
        );
        List<LectureInventoryEntity> lectureInventoryEntities = lectureInventoryJpaRepository.saveAll(
                List.of(
                        new LectureInventoryEntity(null, lectureItemEntities.get(0).getId(), availableSeats),
                        new LectureInventoryEntity(null, lectureItemEntities.get(1).getId(), availableSeats)
                )
        );

        // when
        List<LoadAvailableLectureItemsUseCase.Output> outputs = target.execute(LocalDate.now(), LocalDate.now().plusDays(5L));

        // then
        assertThat(outputs).hasSize(2);
    }

}