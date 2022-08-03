package kz.halykacademy.bookstore.web.books;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class BookDTO {

    private Long id;

    private double price;

    private String publisher;


    private String name;

    private int numOfpage;

    private List<String> authorName;

    private Integer yearOfIssue;

    private List<String>genres;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BookDTO bookDTO = (BookDTO) o;
        return id.equals(bookDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}