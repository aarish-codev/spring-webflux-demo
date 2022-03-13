package com.aarish.webfluxdemo;

import com.aarish.webfluxdemo.dto.Response;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;


public class BadRequestTest extends BaseTest{

    @Autowired
    private WebClient webClient;

    @Test
    public void badRequestTest() {
        Mono<Response> mono = this.webClient
                .get()
                .uri("reactive-math-validation/square/{input}", 5)
                .retrieve()
                .bodyToMono(Response.class)
                .doOnNext(System.out::println)
                .doOnError(err-> System.out.println(err.getMessage()));// Mono<Response>

        StepVerifier.create(mono)
                .verifyError(WebClientResponseException.InternalServerError.class);



    }

    @Test
    public void badRequestTest2() {
        Mono<Response> mono2 = this.webClient
                .get()
                .uri("reactive-math-validation/square/{input}", 12)
                .retrieve()
                .bodyToMono(Response.class)
                .doOnNext(System.out::println)
                .doOnError(err-> System.out.println(err.getMessage()));// Mono<Response>

        StepVerifier.create(mono2)
//                .verifyError(WebClientResponseException.InternalServerError.class);
                .expectNextMatches(res->res.getOutput()==144)
                .verifyComplete();
    }
}
