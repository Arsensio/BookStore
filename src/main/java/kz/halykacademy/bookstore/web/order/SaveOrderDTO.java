package kz.halykacademy.bookstore.web.order;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SaveOrderDTO {
    private Long id;
    private Long user_id;
    private String status;
    private List<Long> books;
    private List<Long>quantity;
}
