package com.hhpluslecture.exception.custom;

public class AlreadyRegisteredLectureException extends RuntimeException {
    public AlreadyRegisteredLectureException(String message) {
        super(message);
    }
}
