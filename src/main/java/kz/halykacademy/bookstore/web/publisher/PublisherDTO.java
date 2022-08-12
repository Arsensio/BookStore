package kz.halykacademy.bookstore.web.publisher;


import kz.halykacademy.bookstore.web.book.BookDTO;
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
    private String label;
    private List<BookDTO>listOfBook;
}
