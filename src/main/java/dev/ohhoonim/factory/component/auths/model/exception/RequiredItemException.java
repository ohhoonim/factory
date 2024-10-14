package dev.ohhoonim.factory.component.auths.model.exception;

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
