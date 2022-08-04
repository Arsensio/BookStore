package kz.halykacademy.bookstore.web.order;

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
    private String status;
    private List<String>books;
    LocalDateTime createdAt;

}
