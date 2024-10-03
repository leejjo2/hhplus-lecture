package com.hhpluslecture.service.port;

import com.hhpluslecture.repository.domain.Lecture;

import java.util.List;

public interface LectureService {

    List<Lecture> loadAllByIds(List<Long> lectureIds);
}
