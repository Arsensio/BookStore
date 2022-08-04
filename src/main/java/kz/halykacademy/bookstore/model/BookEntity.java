package kz.halykacademy.bookstore.model;

import kz.halykacademy.bookstore.web.book.BookDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Min;
import java.util.List;

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
    @JoinTable(name="author_book_table",
            joinColumns={@JoinColumn(name="book_id")},
            inverseJoinColumns={@JoinColumn(name="author_id")})
    List<AuthorEntity> authors;


        @ManyToMany()
        @JoinTable(name="book_genre_table",
                joinColumns={@JoinColumn(name="book_id")},
                inverseJoinColumns={@JoinColumn(name="genre_id")})
    List<GenreEntity>genres;




    public BookDTO toDto() {
        List<String>authorDTOList = List.of();
        if (this.authors != null){
            authorDTOList = this.authors.stream().map(AuthorEntity::getFullName).toList();
        }
        String publisherName = "";
        if (this.publisher!= null){
            publisherName = this.publisher.getName();
        }
        List<String>genres = List.of();
        if (this.genres!=null){
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
