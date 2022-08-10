package kz.halykacademy.bookstore.models;


import kz.halykacademy.bookstore.web.order.OrderDTO;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.*;

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
    private Long userId;

    @Column(name = "order_status")
    private String status;

    @Column(name = "created_At")
    @CreationTimestamp
    LocalDateTime createdAt;

    @OneToMany(mappedBy="order",cascade = CascadeType.ALL)
    List<OrderBookEntity> books = new ArrayList<>();


    public void addBook(BookEntity book,Long quantity) {
        OrderBookEntity orderBookEntity = new OrderBookEntity(this,book,quantity);
        books.add(orderBookEntity);
        book.getOrders().add(orderBookEntity);
        book.setBookQuantity(book.getBookQuantity()-quantity);
    }

    public void removeBook(BookEntity book){
        for (Iterator<OrderBookEntity> iterator = books.iterator(); iterator.hasNext();){
            OrderBookEntity orderBookEntity = iterator.next();
            if (orderBookEntity.getOrder().equals(this)&&orderBookEntity.getBook().equals(book)){
                iterator.remove();
                orderBookEntity.getOrder().getBooks().remove(orderBookEntity);
                orderBookEntity.setBook(null);
                orderBookEntity.setOrder(null);
            }
        }
    }


    public OrderDTO toDTO() {
        return new OrderDTO(
                this.id,
                this.userId,
                this.status,
                null,
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

    @Override
    public String toString() {
        return "OrderEntity{" +
                "id=" + id +
                ", user_id=" + userId +
                ", status='" + status + '\'' +
                ", createdAt=" + createdAt +
                '}';
    }
}
