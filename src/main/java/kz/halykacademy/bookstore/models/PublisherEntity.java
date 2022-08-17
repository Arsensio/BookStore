package kz.halykacademy.bookstore.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import kz.halykacademy.bookstore.web.book.BookDTO;
import kz.halykacademy.bookstore.web.publisher.PublisherDTO;
import lombok.*;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@DynamicUpdate
@Table(name = "publishers")
public class PublisherEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "publishers_name", nullable = false)
    String name;

    public PublisherEntity(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    @JsonIgnore
    @OneToMany(mappedBy = "publisher")
    List<BookEntity> books = new ArrayList<>();


    public PublisherEntity(Long id) {
        this.id = id;
    }

    public PublisherDTO toDTO() {
        List<BookDTO> booksDTOList = List.of();
        if (this.books != null) {
            booksDTOList = this.books.stream().map(BookEntity::toDto).toList();
        }
        return new PublisherDTO(
                this.id,
                this.name,
                booksDTOList
        );

    }


    @Override
    public String toString() {
        return "Publisher{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", books=" + books +
                '}';
    }


}
