package dev.ohhoonim.factory.api.config;

import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import dev.ohhoonim.factory.api.config.DefaultResponseBody.DefaultResponseBodyBuilder;
import dev.ohhoonim.factory.api.config.common.ResponseCodeEnum;

@ControllerAdvice(basePackages = { "dev.ohhoonim.factory.api" })
public class DefaultResponseHandler implements ResponseBodyAdvice<Object> {

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType,
            MediaType selectedContentType, Class selectedConverterType, ServerHttpRequest request,
            ServerHttpResponse response) {
        DefaultResponseBodyBuilder builder = DefaultResponseBody.builder();
        DefaultResponseBody defaultBody =  body == null ? null : (DefaultResponseBody) body;
        if (defaultBody != null && defaultBody.getCode().equals(ResponseCodeEnum.ERROR)) {
            builder
                    .code(defaultBody.getCode())
                    .message(defaultBody.getMessage())
                    .data("")
                    .description(defaultBody.getDescription());
        } else  {
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
