package com.geekbrains.spring.web.core.controllers;

import com.geekbrains.spring.web.api.core.OrderDetailsDto;
import com.geekbrains.spring.web.api.core.OrderDto;
import com.geekbrains.spring.web.core.converters.OrderConverter;
import com.geekbrains.spring.web.core.services.OrderService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/orders")
@RequiredArgsConstructor
@Tag(name = "Заказы", description = "Методы работы с заказами")
public class OrdersController {
    private final OrderService orderService;
    private final OrderConverter orderConverter;

    @Operation(
            summary = "Запрос на создание заказа",
            responses = {
                    @ApiResponse(
                            description = "Успешный ответ", responseCode = "200"
                    )
            }
    )
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createOrder(@RequestHeader String username, @RequestBody OrderDetailsDto orderDetailsDto) {
        orderService.createOrder(username, orderDetailsDto);
    }

    @Operation(
            summary = "Запрос на получение заказов по имени",
            responses = {
                    @ApiResponse(
                            description = "Успешный ответ", responseCode = "200",
                            content = @Content(schema = @Schema(implementation = List.class))
                    )
            }
    )
    @GetMapping
    public List<OrderDto> getCurrentUserOrders(@RequestHeader String username) {
        return orderService.findOrdersByUsername(username).stream()
                .map(orderConverter::entityToDto).collect(Collectors.toList());
    }
}
