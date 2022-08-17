package kz.halykacademy.bookstore.models;

import kz.halykacademy.bookstore.web.cart.CartDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "cart_table")
public class CartEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private UserEntity userId;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "book_id", referencedColumnName = "id")
    private BookEntity book;


    @Column(name = "quantity")
    private Long quantity;

    public CartDTO toDTO() {
        return new CartDTO(
                this.id,
                this.userId,
                this.book,
                this.quantity
        );
    }

    @Override
    public String toString() {
        return "CartEntity{" +
                "id=" + id +
                ", userId=" + userId +
                ", quantity=" + quantity +
                '}';
    }
}
