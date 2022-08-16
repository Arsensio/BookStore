package kz.halykacademy.bookstore.service.interfaces;

import kz.halykacademy.bookstore.exceptions.NotEnoughBooksException;
import kz.halykacademy.bookstore.exceptions.PriceExceedsLimitException;
import kz.halykacademy.bookstore.models.BookEntity;
import kz.halykacademy.bookstore.models.OrderBookEntity;
import kz.halykacademy.bookstore.models.OrderEntity;
import kz.halykacademy.bookstore.models.UserEntity;
import kz.halykacademy.bookstore.store.interfaces.BookRepository;
import kz.halykacademy.bookstore.store.interfaces.OrderRepository;
import kz.halykacademy.bookstore.store.interfaces.UserRepository;
import kz.halykacademy.bookstore.web.order.OrderDTO;
import kz.halykacademy.bookstore.web.order.SaveOrderDTO;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public interface OrderService {

    List<OrderDTO> findAll();

    OrderDTO findOne(Long id) throws Throwable;

    OrderDTO save(UserDetails userDetails, SaveOrderDTO saveUserDTO) throws Exception;

    OrderDTO update(UserDetails userEntity, Long id, SaveOrderDTO orderDTO) throws Exception;

    OrderDTO updateAdmin(Long id, OrderDTO status);

    void delete(Long id);

    List<OrderEntity> findAllByUserId(UserDetails username);
}

@Service
@AllArgsConstructor
class OrderServiceImpl implements OrderService {

    OrderRepository orderRepository;

    BookRepository bookRepository;

    UserRepository userRepository;

    @Override
    public List<OrderDTO> findAll() {
        return orderRepository.findAll().stream().map(OrderEntity::toDTO).collect(Collectors.toList());
    }

    @Override
    public OrderDTO findOne(Long id) throws Throwable {
        return orderRepository.findById(id).get().toDTO();
    }

    @Override
    public OrderDTO save(UserDetails userDetails, SaveOrderDTO orderDTO) throws Exception {
        UserEntity user = userRepository.findByUsernameIgnoreCase(userDetails.getUsername()).get();

        OrderEntity order = new OrderEntity();
        order.setUser(user);
        order.setStatus("Заказ создан");
        order.setCreatedAt(LocalDateTime.now());

        List<BookEntity> foundBooks = findBooks(orderDTO.getBooks());
        List<Long> amounts = orderDTO.getQuantity();

        double check = 0;

        for (int i = 0; i < foundBooks.size(); i++) {
            check += foundBooks.get(i).getPrice() * amounts.get(i);
            if (foundBooks.get(i).getBookQuantity() >= amounts.get(i)) {
                if (check <= 10000)
                    order.addBook(foundBooks.get(i), amounts.get(i));
                else
                    throw new PriceExceedsLimitException("Total price of order should be less than 10 000 KZT");
            } else {
                throw new NotEnoughBooksException("Not enough books");
            }
        }
        return orderRepository.save(order).toDTO();
    }

    @Override
    public OrderDTO update(UserDetails userDetails, Long id, SaveOrderDTO orderDTO) throws Exception {
        UserEntity user = userRepository.findByUsernameIgnoreCase(userDetails.getUsername()).get();

        System.out.println("UPDATE ORDER");
        if (orderRepository.findAllByUser(user).contains(id))
            throw new Exception("GET OUT THE WAY");

        OrderEntity order = orderRepository.findById(id).get();

        System.out.println("FIND BY ID");
        returnBook(order.getBooks());

        List<BookEntity> foundBooks = findBooks(orderDTO.getBooks());
        System.out.println("#1");
        List<Long> amounts = orderDTO.getQuantity();

        System.out.println("#2");

        double check = 0;

        for (int i = 0; i < foundBooks.size(); i++) {
            check += foundBooks.get(i).getPrice() * amounts.get(i);
            if (foundBooks.get(i).getBookQuantity() >= amounts.get(i)) {

                if (check <= 10000)
                    order.addBook(foundBooks.get(i), amounts.get(i));
                else
                    throw new PriceExceedsLimitException("Total price of order should be less than 10 000 KZT");
            } else {
                throw new NotEnoughBooksException("Not enough books");
            }
        }
        System.out.println("#3");


        orderRepository.save(new OrderEntity(
                order.getId(),
                user,
                order.getStatus(),
                order.getCreatedAt(),
                order.getBooks()
        ));
        System.out.println("#4");
        return orderRepository.findById(id).get().toDTO();
    }

    private List<BookEntity> findBooks(List<Long> booksIds) {
        return bookRepository.findAllByIdIn(booksIds);
    }


    @Override
    public OrderDTO updateAdmin(Long id, OrderDTO status) {


        OrderEntity order = orderRepository.findById(id).get();
        return orderRepository.save(new OrderEntity(
                order.getId(),
                order.getUser(),
                status.getStatus(),
                order.getCreatedAt(),
                order.getBooks()
        )).toDTO();

    }

    @Override
    public void delete(Long id) {
        orderRepository.deleteById(id);
    }

    @Override
    public List<OrderEntity> findAllByUserId(UserDetails userDetails) {
        UserEntity user = userRepository.findByUsernameIgnoreCase(userDetails.getUsername()).get();
        return orderRepository.findAllByUser(user);
    }

    private void returnBook(List<OrderBookEntity>list){
        list.forEach(book->{
            BookEntity bookEntity = book.getBook();
            bookEntity.setBookQuantity(
                    bookEntity.getBookQuantity() +  book.getBookQuantity()
            );
            book.setBookQuantity(0L);
        });
        System.out.println("RETURN BOOK");
    }

}