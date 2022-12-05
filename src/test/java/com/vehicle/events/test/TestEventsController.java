package com.vehicle.events.test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.web.reactive.function.client.ClientRequest;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.ExchangeFunction;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@WebFluxTest
public class TestEventsController {

    private static final String BASE_URL = "http://localhost";

    private WebClient webClient;

    @Captor
    private ArgumentCaptor<ClientRequest> argumentCaptor;

    @Mock
    private ExchangeFunction exchangeFunction;

    @BeforeEach
    void init() {
        ClientResponse mockResponse = mock(ClientResponse.class);
        when(mockResponse.bodyToMono(String.class)).thenReturn(Mono.just("test"));
        when(exchangeFunction.exchange(argumentCaptor.capture())).thenReturn(Mono.just(mockResponse));

        webClient = WebClient
                .builder()
                .baseUrl(BASE_URL)
                .exchangeFunction(exchangeFunction)
                .build();
    }

    @Test
    void whenCallSimpleURI_thenURIMatched() {
        webClient.get()
                .uri("/events/events")
                .retrieve()
                .bodyToMono(String.class)
                .block();

        verifyCalledUrl("/events/events");
    }

    private void verifyCalledUrl(String relativeUrl) {
        ClientRequest request = argumentCaptor.getValue();
        assertEquals(String.format("%s%s", BASE_URL, relativeUrl), request.url().toString());

        verify(exchangeFunction).exchange(request);
        verifyNoMoreInteractions(exchangeFunction);
    }


}
