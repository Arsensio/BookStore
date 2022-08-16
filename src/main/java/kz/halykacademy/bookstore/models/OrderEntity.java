package kz.halykacademy.bookstore.models;


import kz.halykacademy.bookstore.web.book.BookDTO;
import kz.halykacademy.bookstore.web.order.OrderDTO;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.*;
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

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private UserEntity user;

    @Column(name = "order_status")
    private String status;

    @Column(name = "created_At")
    @CreationTimestamp
    LocalDateTime createdAt;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    List<OrderBookEntity> books = new ArrayList<>();


    public void addBook(BookEntity book, Long quantity) {
        OrderBookEntity orderBookEntity = new OrderBookEntity(this, book, quantity);
        books.add(orderBookEntity);
        book.getOrders().add(orderBookEntity);
        book.setBookQuantity(book.getBookQuantity() - quantity);
    }

    public void removeBook(BookEntity book) {
        for (Iterator<OrderBookEntity> iterator = books.iterator(); iterator.hasNext(); ) {
            OrderBookEntity orderBookEntity = iterator.next();
            if (orderBookEntity.getOrder().equals(this) && orderBookEntity.getBook().equals(book)) {
                iterator.remove();
                orderBookEntity.getOrder().getBooks().remove(orderBookEntity);
                orderBookEntity.setBook(null);
                orderBookEntity.setOrder(null);
            }
        }
    }


    private List<OrderBookEntity> checkBooks() {
        List<OrderBookEntity> checkedBooks = new ArrayList<>();

        if (this.books != null) {
            this.books.forEach(books -> {
                if (books.getBookQuantity() > 0) {
                    checkedBooks.add(books);
                }
            });
        }
        return checkedBooks;
    }


    public OrderDTO toDTO() {
        List<OrderBookEntity> checkedBooks = checkBooks();
        List<BookEntity> bookList = checkedBooks.stream().map(OrderBookEntity::getBook).toList();


        List<BookDTO> bookDTOList = new ArrayList<>(bookList.stream().map(BookEntity::toDto).toList());
        List<Long> quantity = new ArrayList<>(checkedBooks.stream().map(OrderBookEntity::getBookQuantity).toList());

        return new OrderDTO(
                this.id,
                this.user.getId(),
                this.user.getUsername(),
                this.status,
                bookDTOList,
                quantity,
                this.createdAt
        );
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderEntity that = (OrderEntity) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}
