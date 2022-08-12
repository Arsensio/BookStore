package kz.halykacademy.bookstore.web.book;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class BookDTO {

    private Long id;

    private double price;

    private String publisher;

    private String name;

    private int numOfpages;

    private String bookImage;

    private List<String> authorName;

    private Integer yearOfIssue;

    private List<String> genres;

    private Long bookQuantity;

    private List<Long> authorsIds;
}