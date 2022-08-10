package kz.halykacademy.bookstore.exceptions;

public class NotEnoughBooksException extends RuntimeException {
    public NotEnoughBooksException(String message) {
        super(message);
    }
}
