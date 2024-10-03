package com.hhpluslecture.exception.custom;

public class SeatExceededException extends RuntimeException{
    public SeatExceededException(){
        super("정원 초과");
    }

    public SeatExceededException(String message) {
        super(message);
    }
}
