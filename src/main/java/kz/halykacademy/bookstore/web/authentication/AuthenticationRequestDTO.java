package kz.halykacademy.bookstore.web.authentication;


import lombok.Data;

@Data
public class AuthenticationRequestDTO {

    private String username;
    private String password;

}
