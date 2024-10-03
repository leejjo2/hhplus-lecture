package com.hhpluslecture.exception;

import com.hhpluslecture.exception.custom.AlreadyRegisteredLectureException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    // AlreadyRegisteredLectureException에 대한 예외 처리
    @ExceptionHandler(AlreadyRegisteredLectureException.class)
    public ResponseEntity<String> handleAlreadyRegisteredLectureException(AlreadyRegisteredLectureException ex) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST) // 400 에러 코드 반환
                .body(ex.getMessage()); // 예외 메시지를 응답에 포함
    }
}