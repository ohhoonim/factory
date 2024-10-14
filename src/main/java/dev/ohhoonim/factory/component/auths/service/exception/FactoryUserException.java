package dev.ohhoonim.factory.component.auths.service.exception;

public class FactoryUserException extends RuntimeException{
    public FactoryUserException(String message) {
        super(message);
    }
    public FactoryUserException(Throwable exception) {
        super(exception);
    }
    public FactoryUserException(String message, Throwable exception) {
        super(message, exception);
    }
}
