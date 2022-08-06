package kz.halykacademy.bookstore.exceptions;

public class BlockedUserException extends RuntimeException{
    public BlockedUserException(String message) {
        super(message);
    }
}
