package com.aarish.webfluxdemo.service;

import com.aarish.webfluxdemo.dto.MultiplyRequest;
import com.aarish.webfluxdemo.dto.Response;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;

@Service
public class ReactiveMathService {

    public Mono<Response> getSquare(int input) {
        return Mono.fromSupplier(() -> input * input)
                .map(Response::new);
    }

    public Flux<Response> multiplicationTable(int input) {
        return Flux.range(1, 10)
//                .doOnNext(i->SleepUtil.sleepSeconds(1))
                .delayElements(Duration.ofSeconds(1))
                .doOnNext(i -> System.out.println("Reactive Math service processing: " + i))
                .map(i -> new Response(i * input));
    }

    public Mono<Response> multiply(Mono<MultiplyRequest> requestMono) {
        return requestMono
                .map(request -> request.getFirst() * request.getSecond())
                .map(Response::new);
    }
}
