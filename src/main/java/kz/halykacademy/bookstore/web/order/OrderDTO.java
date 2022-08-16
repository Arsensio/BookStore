package kz.halykacademy.bookstore.web.order;

import kz.halykacademy.bookstore.models.BookEntity;
import kz.halykacademy.bookstore.web.book.BookDTO;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class OrderDTO {
    private Long id;
    private Long user_id;
    private String username;
    private String status;
    private List<BookDTO>books;
    private List<Long>bookQuantity;
    LocalDateTime createdAt;

}
