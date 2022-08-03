package kz.halykacademy.bookstore.web.books;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import kz.halykacademy.bookstore.model.AuthorEntity;
import kz.halykacademy.bookstore.model.GenreEntity;
import kz.halykacademy.bookstore.model.PublisherEntity;
import kz.halykacademy.bookstore.store.interfaces.PublisherRepository;
import kz.halykacademy.bookstore.web.publishers.PublisherDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.relational.core.sql.In;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Min;
import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class SaveBookDTO {
    private Long id;
    private double price;
    private Long publisherId;
    private String name;
    private int numOfPage;
    @Min(1700)
    private Integer yearOfIssue;
    private List<Long>authors;
    private List<Long>genres;

}
