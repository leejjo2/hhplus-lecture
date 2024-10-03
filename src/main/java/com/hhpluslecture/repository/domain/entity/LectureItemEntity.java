package com.hhpluslecture.repository.domain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "LECTURE_ITEM")
public class LectureItemEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JoinColumn(name = "lecture_id", nullable = false)
    private Long lectureId;

    @Column(nullable = false)
    private LocalDate date;  // 강의 날짜

    @Column(nullable = false)
    private int capacity;  // 최대 수강 인원

}
