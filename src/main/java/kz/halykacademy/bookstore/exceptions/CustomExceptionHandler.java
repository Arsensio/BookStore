package kz.halykacademy.bookstore.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.RestClientResponseException;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(value = Exception.class)
    protected ResponseEntity<Object> handleExceptionInternal(Exception ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new MyError(ex.getMessage(),LocalDateTime.now()));
    }

    @ExceptionHandler(value = ResourceNotFoundException.class)
    protected ResponseEntity<Object>handleNotFound(ResourceNotFoundException ex){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new MyError(ex.getMessage(),LocalDateTime.now()));
    }


    @ExceptionHandler(value = BlockedUserException.class)
    protected ResponseEntity<Object>handleBlockedUserException(BlockedUserException exception){
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new MyError(exception.getMessage(),LocalDateTime.now()));
    }

    @ExceptionHandler(value = PriceExceedsLimitException.class)
    protected ResponseEntity<Object>handlePriceLimitException(PriceExceedsLimitException exception){
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new MyError(exception.getMessage(),LocalDateTime.now()));
    }



}

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
class MyError{

    private String message;
    private LocalDateTime timestemp;

}
