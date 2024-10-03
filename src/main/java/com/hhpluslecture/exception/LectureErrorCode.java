package com.hhpluslecture.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum LectureErrorCode implements ErrorCode {
    ENROLLMENT_EXCEED_CAPACITY(HttpStatus.BAD_REQUEST, "강의 등록이 정원을 초과했습니다."),
    ALREADY_REGISTERED_LECTURE(HttpStatus.BAD_REQUEST, "이미 등록한 강의입니다."),
    LECTURE_NOT_FOUND(HttpStatus.NOT_FOUND, "존재하지 않는 강의입니다."),
    LECTURE_ITEM_NOT_FOUND(HttpStatus.NOT_FOUND, "존재하지 않는 강의목록입니다.")
    ;


    private final HttpStatus status;
    private final String message;

    @Override
    public String getCode() {
        return name();
    }

    @Override
    public HttpStatus getStatus() {
        return status;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
