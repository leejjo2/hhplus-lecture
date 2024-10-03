package com.hhpluslecture.repository.domain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "LECTURE_INVENTORY")
public class LectureInventoryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;  // Primary Key

    @Column(name = "lecture_id", nullable = false)
    private Long lectureId;  // Lecture와 ManyToOne 관계

    @Column(name = "lecture_item_id", nullable = false, unique = true)
    private Long lectureItemId;  // LectureItem과 ManyToOne 관계

    @Column(nullable = false)
    private int availableSeats;  // 남은 수강 가능 인원

}
