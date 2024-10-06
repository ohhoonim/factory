package dev.ohhoonim.factory.domain.comment.api;

public class RequiredException extends RuntimeException{
    public RequiredException(String message, Throwable throwable) {
        super(message, throwable);
    }
}
