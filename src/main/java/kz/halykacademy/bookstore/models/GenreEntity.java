package kz.halykacademy.bookstore.models;


import com.fasterxml.jackson.annotation.JsonIgnore;
import kz.halykacademy.bookstore.web.genre.GenreDTO;
import lombok.*;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

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


    @JsonIgnore
    @ManyToMany(mappedBy = "genres")
    private List<BookEntity> books;

    public GenreEntity(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GenreEntity that = (GenreEntity) o;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public GenreDTO toDTO() {
        return new GenreDTO(
                this.id,
                this.name
        );
    }

}
