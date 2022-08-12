package kz.halykacademy.bookstore.web.publisher;

import kz.halykacademy.bookstore.models.BookEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class SavePublisherDTO  {
    String name;
}
