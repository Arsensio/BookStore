package kz.halykacademy.bookstore.web.author;

import com.fasterxml.jackson.annotation.JsonFormat;
import kz.halykacademy.bookstore.model.AuthorEntity;
import kz.halykacademy.bookstore.model.BookEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class SaveAuthorDTO {
    private Long id;

    private String firstName;

    private String lastName;

    private String patronymic;

    private List<BookEntity> books;

    @JsonFormat(pattern = "dd/MM/yyyy")
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private LocalDate dateOfBirth;
}
