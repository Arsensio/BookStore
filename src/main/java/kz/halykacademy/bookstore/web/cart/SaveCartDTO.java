package kz.halykacademy.bookstore.web.cart;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class SaveCartDTO {
    private Long bookId;
    private Long quantity;
}
