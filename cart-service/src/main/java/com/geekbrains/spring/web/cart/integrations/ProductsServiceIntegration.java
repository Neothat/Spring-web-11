package com.geekbrains.spring.web.cart.integrations;

import com.geekbrains.spring.web.api.core.ProductDto;
import com.geekbrains.spring.web.cart.exceptions.ProductServiceIntegrationException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class ProductsServiceIntegration {

    private final WebClient coreServiceWebClient;

    public ProductDto findById(Long id) {
        return coreServiceWebClient.get()
                .uri("/api/v1/products/" + id.toString())
                .retrieve()
//                Если использовать onStatus, то будем падать с:
//                java.lang.NoSuchMethodException: org.springframework.web.reactive.function.client.WebClient$UriSpec.uri(java.lang.String,java.lang.Object)

//                .onStatus(HttpStatus::is4xxClientError, clientResponse ->
//                        Mono.error(new ProductServiceIntegrationException("Сервис продуктов не доступен")))
//                .onStatus(HttpStatus::is5xxServerError, clientResponse ->
//                        Mono.error(new ProductServiceIntegrationException("Сервис продуктов сломался")))
                .bodyToMono(ProductDto.class)
                .block();
    }
}
