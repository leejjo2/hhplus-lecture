package com.hhpluslecture.service;

import com.hhpluslecture.repository.domain.Lecture;
import com.hhpluslecture.repository.port.LectureRepository;
import com.hhpluslecture.service.port.LectureService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LectureServiceImpl implements LectureService {
    private final LectureRepository lectureRepository;


    @Override
    public List<Lecture> loadAllByIds(List<Long> lectureIds) {
        return lectureRepository.findAllByIds(lectureIds);
    }
}
