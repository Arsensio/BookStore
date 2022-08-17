package kz.halykacademy.bookstore.exceptions;

public class UsernameAlreadyExistException extends RuntimeException{
    public UsernameAlreadyExistException(String message) {
        super(message);
    }
}
