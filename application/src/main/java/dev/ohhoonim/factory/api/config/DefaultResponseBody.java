package dev.ohhoonim.factory.api.config;

import dev.ohhoonim.factory.api.config.common.ResponseCodeEnum;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class DefaultResponseBody {
    private ResponseCodeEnum code;
    private String message;
    private Object data;
    private String description;

    @Builder
    public DefaultResponseBody(ResponseCodeEnum code, String message, Object data, String description) {
        this.code = code;
        this.message = message;
        this.data = data;
        this.description = description;
    }
}
