package com.aarish.webfluxdemo.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;

@Data
@ToString
@NoArgsConstructor
public class Response {
    private Date time = new Date();
    private int output;

    public Response(int output) {
        this.output = output;
    }
}
