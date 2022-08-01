package kz.halykacademy.bookstore.model;


import kz.halykacademy.bookstore.web.genre.GenreDTO;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Genres")
@ToString
public class GenreEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "genre_name", nullable = false)
    private String name;

    @ManyToMany(mappedBy = "genres", fetch = FetchType.LAZY)
    private List<BookEntity>books;

    public GenreDTO toDTO(){
        return new GenreDTO(
                this.id,
                this.name
        );
    }

}
