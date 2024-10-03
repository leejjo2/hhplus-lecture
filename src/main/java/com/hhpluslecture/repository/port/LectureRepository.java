package com.hhpluslecture.repository.port;

import com.hhpluslecture.repository.domain.Lecture;

import java.util.List;

public interface LectureRepository {

    List<Lecture> findAllByIds(List<Long> lectureIds);
}
