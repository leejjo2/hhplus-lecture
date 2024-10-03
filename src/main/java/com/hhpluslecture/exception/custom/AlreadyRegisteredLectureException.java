package com.hhpluslecture.exception.custom;

public class AlreadyRegisteredLectureException extends RuntimeException {
    public AlreadyRegisteredLectureException(){
        super("이미 등록한 강의입니다.");
    }
    public AlreadyRegisteredLectureException(String message) {
        super(message);
    }
}
