package com.gcirit.demo.stockclient;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

import static org.junit.jupiter.api.Assertions.*;

class WebClientStockClientIntegrationTest {
    private WebClient webClient = WebClient.builder().build();
    @Test
    void shouldRetrieveTheStockPricesFromTheService() {
        WebClientStockClient webClientStockClient = new WebClientStockClient(webClient);
        Flux<StockPrice> prices = webClientStockClient.pricesFor("SYMBOL");

        Assertions.assertNotNull(prices);
        Assertions.assertTrue(prices.take(5).count().block() > 0);
    }
}