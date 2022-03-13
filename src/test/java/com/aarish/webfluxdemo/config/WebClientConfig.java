package com.aarish.webfluxdemo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.ClientRequest;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.ExchangeFunction;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Configuration
public class WebClientConfig {

    @Bean
    public WebClient webClient() {
        return WebClient.builder()
                .baseUrl("http://localhost:8080")
//                .defaultHeaders(h -> h.setBasicAuth("testuser","testpass")) // Set default headers in every request
                .filter(this::tokenGenerator)
                .build();

    }

    private Mono<ClientResponse> tokenGenerator(ClientRequest request, ExchangeFunction exchangeFunction){
        System.out.println("Generating Token...");
        ClientRequest clientRequest = ClientRequest.from(request).
                headers(h -> h.set("header1", "value1"))
                .headers(h -> h.set("header2", "value2"))
//                .headers(h -> h.setBasicAuth("testuser", "testpass"))
                .headers(h -> h.setBearerAuth("some-token"))
                .build();
        return exchangeFunction.exchange(clientRequest);

    }
}
