package kz.halykacademy.bookstore.models;


import kz.halykacademy.bookstore.web.order.OrderDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "orders")
public class OrderEntity {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id")
    private Long user_id;

    @Column(name = "order_status")
    private String status;

    @Column(name = "created_At")
    @CreationTimestamp
    LocalDateTime createdAt;

    @ManyToMany()
    @JoinTable(name = "order_book_table",
            joinColumns = {@JoinColumn(name = "order_id")},
            inverseJoinColumns = {@JoinColumn(name = "book_id")})
    List<BookEntity> books;

    public OrderDTO toDTO() {
        return new OrderDTO(
                this.id,
                this.user_id,
                this.status,
                this.books.stream().map(BookEntity::getName).collect(Collectors.toList()),
                this.createdAt
        );
    }

}
