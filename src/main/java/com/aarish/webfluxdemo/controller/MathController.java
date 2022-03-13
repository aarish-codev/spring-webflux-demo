package com.aarish.webfluxdemo.controller;

import com.aarish.webfluxdemo.dto.Response;
import com.aarish.webfluxdemo.service.MathService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("math")
public class MathController {

    private MathService mathService;

    public MathController(MathService mathService) {
        this.mathService = mathService;
    }

    @GetMapping("square/{input}")
    public Response findSquare(@PathVariable int input) {
        return this.mathService.getSquare(input);
    }

    @GetMapping("table/{input}")
    public List<Response> getTable(@PathVariable int input) {
        return this.mathService.multiplicationTable(input);
    }
}
