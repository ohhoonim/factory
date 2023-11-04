package dev.ohhoonim.factory.domain.auth.infra;

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
