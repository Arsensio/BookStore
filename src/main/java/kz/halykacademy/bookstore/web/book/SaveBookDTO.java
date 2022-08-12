package kz.halykacademy.bookstore.web.book;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.validation.constraints.Min;
import java.util.LinkedHashSet;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class SaveBookDTO {
    private double price;
    private Long publisherId;
    private String name;
    private int numOfPages;
    private Long bookQuantity;
    private String bookImage;
    @Min(1700)
    private Integer yearOfIssue;
    private LinkedHashSet<Long>authors;
    private LinkedHashSet<Long> genres;

}
