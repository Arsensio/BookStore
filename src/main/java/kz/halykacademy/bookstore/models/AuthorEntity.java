package kz.halykacademy.bookstore.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import kz.halykacademy.bookstore.web.author.AuthorDTO;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "authors")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class AuthorEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "patronymic")
    private String patronymic;

    @Column(name = "date_of_birth")
    private LocalDate dateOfBirth;

    @JsonIgnore
    @ManyToMany(mappedBy = "authors")
    private List<BookEntity> books;


    @CreationTimestamp
    private LocalDateTime createdAt;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AuthorEntity author = (AuthorEntity) o;
        return id.equals(author.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public AuthorDTO toDto() {

        List<String> books = List.of();
        if (this.books != null)
            books = this.books.stream().map(BookEntity::getName).toList();

        return new AuthorDTO(
                this.id,
                this.firstName,
                this.lastName,
                this.patronymic,
                this.dateOfBirth,
                books
        );
    }

    public String getFullName(){
        return this.firstName+" "+this.lastName+" "+this.patronymic;
    }
}

