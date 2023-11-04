package dev.ohhoonim.factory.domain.auth.api;

public class RequiredItemException extends RuntimeException{
    public RequiredItemException(String message) {
        super(message);
    }
    public RequiredItemException(Throwable exception) {
        super(exception);
    }
    public RequiredItemException(String message, Throwable exception) {
        super(message, exception);
    }
}
