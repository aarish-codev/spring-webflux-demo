package com.aarish.webfluxdemo.dto;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class InvalidRequestResponse {
    private int errorCode;
    private int input;
    private String message;

}
