package kz.halykacademy.bookstore.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "book_genre_table")
@Entity
public class BookGenreEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "book_id")
    private Long bookId;

    @Column(name = "genre_id")
    private Long genreId;


    public BookGenreEntity(Long bookId, Long genreId) {
        this.genreId = genreId;
        this.bookId = bookId;

    }

}
