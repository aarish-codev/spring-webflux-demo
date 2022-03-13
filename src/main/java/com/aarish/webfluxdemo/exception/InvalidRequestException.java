package com.aarish.webfluxdemo.exception;

import lombok.Getter;

@Getter
public class InvalidRequestException extends RuntimeException{
    private static final String MSG = "allowed range is 10 - 20";
    private  final int errorCode = 450;
    private final int input;

    public InvalidRequestException( int input){
        super(MSG);
        this.input = input;
    }


}
