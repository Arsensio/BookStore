package kz.halykacademy.bookstore.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import kz.halykacademy.bookstore.web.book.BookDTO;
import lombok.*;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.NaturalIdCache;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;
import javax.validation.constraints.Min;
import java.util.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "books")
@NaturalIdCache
@Cacheable
public class BookEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;


    @Column(name = "price")
    double price;

    @Column(name = "book_quantity")
    Long bookQuantity;

    @ManyToOne
    @JoinColumn(name = "publisher_id", referencedColumnName = "id")
    PublisherEntity publisher;

    @Column(name = "book_name")
    String name;

    @Column(name = "num_of_page")
    int numOfpage;


    @Column(name = "year_Of_Issue")
    @Min(1700)
    Integer yearOfIssue;


    @ManyToMany
    @JoinTable(name = "author_book_table",
            joinColumns = {@JoinColumn(name = "book_id")},
            inverseJoinColumns = {@JoinColumn(name = "author_id")})
    List<AuthorEntity> authors;


    @ManyToMany
    @JoinTable(name = "book_genre_table",
            joinColumns = {@JoinColumn(name = "book_id")},
            inverseJoinColumns = {@JoinColumn(name = "genre_id")})
    List<GenreEntity> genres;


    @OneToMany(mappedBy = "book",cascade =CascadeType.MERGE)
    List<OrderBookEntity> orders = new ArrayList<>();


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
        return "BookEntity{" +
                "id=" + id +
                ", price=" + price +
                ", publisher=" + publisher +
                ", name='" + name + '\'' +
                ", numOfpage=" + numOfpage +
                ", yearOfIssue=" + yearOfIssue +
                '}';
    }
}
