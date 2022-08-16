package kz.halykacademy.bookstore.web.cart;

import kz.halykacademy.bookstore.models.BookEntity;
import kz.halykacademy.bookstore.models.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CartDTO {
    private Long id;
    private UserEntity userId;
    private BookEntity bookId;
    private Long quantity;
}
