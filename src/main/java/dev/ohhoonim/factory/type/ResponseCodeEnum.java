package dev.ohhoonim.factory.type;

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
