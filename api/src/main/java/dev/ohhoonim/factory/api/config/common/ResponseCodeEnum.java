package dev.ohhoonim.factory.api.config.common;

public enum ResponseCodeEnum {
    SUCCESS("success"),
    ERROR("fail");

    private final String description;

    ResponseCodeEnum(final String description) {
        this.description = description;
    }

    public String toString() {
        return description;
    }
}
