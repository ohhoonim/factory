package dev.ohhoonim.factory.api.config;

import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import dev.ohhoonim.factory.api.config.common.ResponseCodeEnum;
import jakarta.servlet.http.HttpServletRequest;

@ControllerAdvice(basePackages = { "dev.ohhoonim.factory.api" })
public class DefaultExceptionHandler {

    @ExceptionHandler(Exception.class)
    ResponseEntity<DefaultResponseBody> defaultErrorHandler(HttpServletRequest request, Exception e)
            throws Exception {
        String [] messages = e.getMessage().split(":");
        String message = "";
        if (messages != null && messages.length > 0) {
            message = messages[0];
        }
        return ResponseEntity.ok()
                .body(DefaultResponseBody.builder()
                        .code(ResponseCodeEnum.ERROR)
                        .message(message)
                        .data(null)
                        .description(ResponseCodeEnum.ERROR.toString())
                        .build());
    }
}
