package kz.halykacademy.bookstore.web.publishers;


import kz.halykacademy.bookstore.web.books.BookDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class PublisherDTO {

    private Long id;
    private String name;
    private List<BookDTO>listOfBook;
}
