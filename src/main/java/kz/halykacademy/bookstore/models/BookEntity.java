package kz.halykacademy.bookstore.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import kz.halykacademy.bookstore.web.book.BookDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Min;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "books")
public class BookEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;


    @Column(name = "price")
    double price;


    @ManyToOne()
    @JoinColumn(name = "publisher_id", referencedColumnName = "id")
    PublisherEntity publisher;

    @Column(name = "book_name")
    String name;

    @Column(name = "num_of_page")
    int numOfpage;


    @Column(name = "year_Of_Issue")
    @Min(1700)
    Integer yearOfIssue;


    @ManyToMany()
    @JoinTable(name = "author_book_table",
            joinColumns = {@JoinColumn(name = "book_id")},
            inverseJoinColumns = {@JoinColumn(name = "author_id")})
    List<AuthorEntity> authors;


    @ManyToMany()
    @JoinTable(name = "book_genre_table",
            joinColumns = {@JoinColumn(name = "book_id")},
            inverseJoinColumns = {@JoinColumn(name = "genre_id")})
    List<GenreEntity> genres;

    @JsonIgnore
    @ManyToMany(mappedBy = "books")
    List<OrderEntity>orderEntities;



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BookEntity book = (BookEntity) o;
        return id.equals(book.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public BookDTO toDto() {
        List<String> authorDTOList = List.of();
        if (this.authors != null) {
            authorDTOList = this.authors.stream().map(AuthorEntity::getFullName).toList();
        }
        String publisherName = "";
        if (this.publisher != null) {
            publisherName = this.publisher.getName();
        }
        List<String> genres = List.of();
        if (this.genres != null) {
            genres = this.genres.stream().map(GenreEntity::getName).toList();
        }

        return new BookDTO(
                this.id,
                this.price,
                publisherName,
                this.name,
                this.numOfpage,
                authorDTOList,
                this.yearOfIssue,
                genres
        );

    }


    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", price=" + price +
                ", authors=" + authors +
                ", publisher='" + publisher + '\'' +
                ", name='" + name + '\'' +
                ", numOfpage=" + numOfpage +
                ", yearOfIssue=" + yearOfIssue +
                '}';
    }
}
