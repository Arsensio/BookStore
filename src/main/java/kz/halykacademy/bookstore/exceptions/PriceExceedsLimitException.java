package kz.halykacademy.bookstore.exceptions;

public class PriceExceedsLimitException extends RuntimeException {
    public PriceExceedsLimitException(String message) {
        super(message);
    }
}
