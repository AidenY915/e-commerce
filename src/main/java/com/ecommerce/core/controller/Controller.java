package com.ecommerce.core.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Tag(name = "Test", description = "테스트용 API")
public class Controller {

    @Operation(
            summary = "테스트용 엔드포인트",
            description = "단순히 'hello' 문자열을 반환합니다."
    )
    @ApiResponse(
            responseCode = "200",
            description = "성공 응답",
            content = @Content(
                    mediaType = "text/plain",
                    schema = @Schema(implementation = String.class),
                    examples = {}
            )
    )
    @GetMapping("/")
    public String test() {
        return "hello";
    }

}
