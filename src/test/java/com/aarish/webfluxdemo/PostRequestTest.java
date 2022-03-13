package com.aarish.webfluxdemo;

import com.aarish.webfluxdemo.dto.MultiplyRequest;
import com.aarish.webfluxdemo.dto.Response;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

public class PostRequestTest extends BaseTest {

    @Autowired
    private WebClient webClient;

    @Test
    public void multiplyTest() {
        Mono<Response> responseMono = this.webClient
                .post()
                .uri("reactive-math/multiply")
                .bodyValue(multiplyRequest(2, 5))
//                .headers(h->h.set("someKey","somevalue"))
                .retrieve()
                .bodyToMono(Response.class)
                .doOnNext(System.out::println);

        StepVerifier.create(responseMono)
                .expectNextCount(1)
                .verifyComplete();

    }

    private MultiplyRequest multiplyRequest(int a, int b) {
        MultiplyRequest request = new MultiplyRequest();
        request.setFirst(a);
        request.setSecond(b);
        return request;
    }
}
