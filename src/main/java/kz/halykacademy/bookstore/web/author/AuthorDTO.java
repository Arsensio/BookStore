package kz.halykacademy.bookstore.web.author;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class AuthorDTO {

    private Long id;

    private String firstName;
    private String lastName;
    private String patronymic;
    private LocalDate dateOfBirth;
    private List<String> books;
}