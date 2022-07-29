package kz.halykacademy.bookstore.model;

import kz.halykacademy.bookstore.web.author.AuthorDTO;
import kz.halykacademy.bookstore.web.books.BookDTO;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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

    @ManyToMany(mappedBy = "author", fetch = FetchType.LAZY)
    private List<BookEntity> books = new ArrayList<>();

    @CreationTimestamp
    private LocalDateTime createdAt;

    public AuthorDTO toDto() {

        List<BookDTO> books = List.of();
        if (this.books != null)
            books = this.books.stream().map(BookEntity::toDto).toList();

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

