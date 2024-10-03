package com.hhpluslecture.repository;

import com.hhpluslecture.repository.domain.Lecture;
import com.hhpluslecture.repository.orm.LectureJpaRepository;
import com.hhpluslecture.repository.port.LectureRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class LectureRepositoryImpl implements LectureRepository {
    private final LectureJpaRepository lectureJpaRepository;

    @Override
    public List<Lecture> findAllByIds(List<Long> lectureIds) {
        return lectureJpaRepository.findAllByIdIn(lectureIds).stream().map(Lecture::fromEntity).collect(Collectors.toList());
    }
}
