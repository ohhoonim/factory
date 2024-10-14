package dev.ohhoonim.factory.configuration;

import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import dev.ohhoonim.factory.configuration.DefaultResponseBody.DefaultResponseBodyBuilder;
import dev.ohhoonim.factory.type.ResponseCodeEnum;

@ControllerAdvice(basePackages = { "dev.ohhoonim.factory" })
public class DefaultResponseHandler implements ResponseBodyAdvice<Object> {

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType,
            MediaType selectedContentType, Class selectedConverterType, ServerHttpRequest request,
            ServerHttpResponse response) {
        DefaultResponseBodyBuilder builder = DefaultResponseBody.builder();
        // DefaultResponseBody defaultBody = body == null ? null : (DefaultResponseBody)
        // body;
        if (body != null && (body instanceof DefaultResponseBody)) {
            DefaultResponseBody defaultBody = (DefaultResponseBody) body;
            if (defaultBody.getCode().equals(ResponseCodeEnum.ERROR)) {
                builder
                        .code(defaultBody.getCode())
                        .message(defaultBody.getMessage())
                        .data("")
                        .description(defaultBody.getDescription());
            }
        } else {
            builder
                    .code(ResponseCodeEnum.SUCCESS)
                    .message(ResponseCodeEnum.SUCCESS.toString())
                    .data(body);
        }

        return builder.build();
    }

    @Override
    public boolean supports(MethodParameter returnType, Class converterType) {
        return true;
    }

}
