package kz.halykacademy.bookstore.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "order_book_table")
@Getter
@Setter
public class OrderBookEntity {

    @EmbeddedId
    private OrderBookId id = new OrderBookId();

    @ManyToOne
    @MapsId("orderId")
    @JoinColumn(name = "order_id", referencedColumnName = "id")
    private OrderEntity order;

    @ManyToOne
    @MapsId("bookId")
    @JoinColumn(name = "book_id", referencedColumnName = "id")
    private BookEntity book;


    @Column(name = "book_quantity")
    private Long bookQuantity;


    public OrderBookEntity(OrderEntity order, BookEntity book, Long bookQuantity) {
        this.order = order;
        this.book = book;
        this.id = new OrderBookId(order.getId(), book.getId());
        this.bookQuantity = bookQuantity;
    }

    public OrderBookEntity() {

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderBookEntity that = (OrderBookEntity) o;
        return Objects.equals(order, that.order)
                && Objects.equals(book, that.book);
    }

    @Override
    public int hashCode() {
        return Objects.hash(order, book);
    }
}
