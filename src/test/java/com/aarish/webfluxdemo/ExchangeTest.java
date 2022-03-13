package com.aarish.webfluxdemo;

import com.aarish.webfluxdemo.dto.Response;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.InputMismatchException;

public class ExchangeTest extends BaseTest {

    @Autowired
    private WebClient webClient;

    @Test
    public void exchangeTest1() {
        Mono<Object> mono = this.webClient
                .get()
                .uri("reactive-math-validation/square/{input}", 5)
                .exchangeToMono(this::exchangeTest)
                .doOnNext(System.out::println)
                .doOnError(err-> System.out.println(err.getMessage()));// Mono<Response>

        StepVerifier.create(mono)
                .expectNextCount(1)
                .verifyComplete();



    }

    @Test
    public void exchangeTest2() {
        Mono<Object> mono2 = this.webClient
                .get()
                .uri("reactive-math-validation/square/{input}", 12)
                .exchangeToMono(this::exchangeTest)
                .doOnNext(System.out::println)
                .doOnError(err-> System.out.println(err.getMessage()));// Mono<Response>

        StepVerifier.create(mono2)
                .expectNextCount(1)
                .verifyComplete();
    }

    private Mono<Object> exchangeTest(ClientResponse clientResponse){
        if(clientResponse.rawStatusCode()==500)
            return clientResponse.bodyToMono(InputMismatchException.class);
        else
            return clientResponse.bodyToMono(Response.class);
    }
}

