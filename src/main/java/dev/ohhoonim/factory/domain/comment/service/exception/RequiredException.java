package dev.ohhoonim.factory.domain.comment.service.exception;

public class RequiredException extends RuntimeException{
    public RequiredException(String message, Throwable throwable) {
        super(message, throwable);
    }
}
