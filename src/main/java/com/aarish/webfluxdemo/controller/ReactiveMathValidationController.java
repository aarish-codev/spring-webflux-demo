package com.aarish.webfluxdemo.controller;

import com.aarish.webfluxdemo.dto.MultiplyRequest;
import com.aarish.webfluxdemo.dto.Response;
import com.aarish.webfluxdemo.exception.InvalidRequestException;
import com.aarish.webfluxdemo.service.ReactiveMathService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Map;

@RestController
@RequestMapping("reactive-math-validation")
public class ReactiveMathValidationController {

    private ReactiveMathService reactiveMathService;

    public ReactiveMathValidationController(ReactiveMathService reactiveMathService) {
        this.reactiveMathService = reactiveMathService;
    }

    @GetMapping("square/{input}")
    public Mono<Response> findSquare(@PathVariable int input) {
        // Non reactive way to handle exception
//        if(input<10 || input>20)
//            throw new InvalidRequestException(input);
//        return this.reactiveMathService.getSquare(input);

         // reactive way
             return Mono.just(input)
                     .handle( (integer, sink) -> {
                         if(integer<10 || integer >20)
                             sink.error(new InvalidRequestException(input));
                         else
                            sink.next(integer);
                        })
                     .cast(Integer.class)
                     .flatMap(i-> this.reactiveMathService.getSquare(i));

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
