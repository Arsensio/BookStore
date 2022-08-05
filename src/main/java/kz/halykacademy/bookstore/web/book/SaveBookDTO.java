package kz.halykacademy.bookstore.web.book;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.validation.constraints.Min;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class SaveBookDTO {
    private Long id;
    private double price;
    private Long publisherId;
    private String name;
    private int numOfpage;
    @Min(1700)
    private Integer yearOfIssue;
    private List<Long>authors;
    private List<Long>genres;

}
