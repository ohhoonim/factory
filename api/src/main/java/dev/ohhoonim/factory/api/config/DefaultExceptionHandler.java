package dev.ohhoonim.factory.api.config;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import jakarta.servlet.http.HttpServletRequest;

@ControllerAdvice(basePackages = {"dev.ohhoonim.factory.api"})
public class DefaultExceptionHandler {

    @ExceptionHandler(Exception.class)
    DefaultResponseBody defaultErrorHandler(HttpServletRequest request, Exception e)
            throws Exception {
        return DefaultResponseBody.builder()
                .code("ERR0001")
                .message(e.getMessage())
                .data(null)
                .description("fail")
        .build();
    }
}
