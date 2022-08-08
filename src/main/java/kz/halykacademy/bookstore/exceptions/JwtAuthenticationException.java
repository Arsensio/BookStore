package kz.halykacademy.bookstore.exceptions;


import org.springframework.security.core.AuthenticationException;

public class JwtAuthenticationException extends AuthenticationException {
    public JwtAuthenticationException(String msg, Throwable t){
        super(msg,t);
    }

    public JwtAuthenticationException(String s){
        super(s);
    }
}
