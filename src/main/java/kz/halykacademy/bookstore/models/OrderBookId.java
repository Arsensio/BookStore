package kz.halykacademy.bookstore.models;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class OrderBookId implements Serializable {
    private Long orderId;
    private Long bookId;

    public OrderBookId() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderBookId that = (OrderBookId) o;
        return Objects.equals(orderId, that.orderId) && Objects.equals(bookId, that.bookId);
    }

    public OrderBookId(Long order, Long book) {
        this.orderId = order;
        this.bookId = book;
    }

    @Override
    public int hashCode() {
        return Objects.hash(orderId, bookId);
    }
}
