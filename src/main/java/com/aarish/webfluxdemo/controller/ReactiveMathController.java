package com.aarish.webfluxdemo.controller;

import com.aarish.webfluxdemo.dto.Response;
import com.aarish.webfluxdemo.dto.MultiplyRequest;
import com.aarish.webfluxdemo.service.ReactiveMathService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Map;

@RestController
@RequestMapping("reactive-math")
public class ReactiveMathController {

    private ReactiveMathService reactiveMathService;

    public ReactiveMathController(ReactiveMathService reactiveMathService) {
        this.reactiveMathService = reactiveMathService;
    }

    @GetMapping("square/{input}")
    public Mono<Response> findSquare(@PathVariable int input) {
        return this.reactiveMathService.getSquare(input);
    }

    @GetMapping("table/{input}")
    public Flux<Response> getTable(@PathVariable int input) {
        return this.reactiveMathService.multiplicationTable(input);
    }

    @GetMapping(value = "table/stream/{input}", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<Response> getTableStream(@PathVariable int input) {
        return this.reactiveMathService.multiplicationTable(input);
    }

    @PostMapping("multiply")
    public Mono<Response> multiply(@RequestBody Mono<MultiplyRequest> requestMono,
                                    @RequestHeader Map<String, String> headers){
        System.out.println(headers);
        return this.reactiveMathService.multiply(requestMono);
    }
}
