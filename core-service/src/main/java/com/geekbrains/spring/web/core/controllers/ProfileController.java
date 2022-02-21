package com.geekbrains.spring.web.core.controllers;

import com.geekbrains.spring.web.api.core.ProfileDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/profile")
@Tag(name = "Пользователи", description = "Методы работы с пользователями")
public class ProfileController {

    @Operation(
            summary = "Запрос на получение профиля по имени",
            responses = {
                    @ApiResponse(
                            description = "Успешный ответ", responseCode = "200",
                            content = @Content(schema = @Schema(implementation = ProfileDto.class))
                    )
            }
    )
    @GetMapping
    public ProfileDto getCurrentUserInfo(@RequestHeader String username) {
        return new ProfileDto(username);
    }
}
