package kz.halykacademy.bookstore;

import kz.halykacademy.bookstore.model.AuthorEntity;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.Bean;


import java.util.List;
import java.util.stream.Stream;

@SpringBootApplication
public class Main  {
    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }
}
