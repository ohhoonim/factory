package dev.ohhoonim.factory.domain.auth.infra;

public class StmpSendException extends RuntimeException {
    public StmpSendException(String message) {
        super(message);
    }

    public StmpSendException(Throwable exception) {
        super(exception);
    }

    public StmpSendException(String message, Throwable exception) {
        super(message, exception);
    }
}
