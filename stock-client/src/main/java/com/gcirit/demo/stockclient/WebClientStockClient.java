package com.gcirit.demo.stockclient;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

import java.io.IOException;
@Slf4j
public class WebClientStockClient implements StockClient {

    private WebClient webClient;

    public WebClientStockClient( WebClient webClient){
        this.webClient = webClient;
    }

    public WebClientStockClient() {
    }

    @Override
    public Flux<StockPrice> pricesFor(String symbol) {
       return webClient.get()
                .uri("http://localhost:8080/stocks/{symbol}", symbol)
                .retrieve()
            .bodyToFlux(StockPrice.class).doOnError(IOException.class, e -> log.error(e.getMessage()));
    }
}
