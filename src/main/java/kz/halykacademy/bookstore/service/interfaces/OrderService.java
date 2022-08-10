package kz.halykacademy.bookstore.service.interfaces;

import kz.halykacademy.bookstore.exceptions.NotEnoughBooksException;
import kz.halykacademy.bookstore.exceptions.PriceExceedsLimitException;
import kz.halykacademy.bookstore.models.*;
import kz.halykacademy.bookstore.store.interfaces.BookRepository;
import kz.halykacademy.bookstore.store.interfaces.OrderRepository;
import kz.halykacademy.bookstore.store.interfaces.UserRepository;
import kz.halykacademy.bookstore.web.order.OrderDTO;
import kz.halykacademy.bookstore.web.order.SaveOrderDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public interface OrderService {

    public List<OrderDTO> findAll();

    public OrderDTO findOne(Long id) throws Throwable;

    public OrderDTO save(UserDetails userDetails, SaveOrderDTO saveUserDTO) throws Exception;

    public OrderDTO update(UserDetails userEntity, Long id, SaveOrderDTO orderDTO) throws Exception;

    public OrderDTO updateAdmin(Long id, SaveOrderDTO orderDTO);

    public void delete(Long id);

    List<OrderEntity> findAllByUserId(UserDetails username);
}

@Service
class OrderServiceImpl implements OrderService {
    @Autowired
    OrderRepository orderRepository;

    @Autowired
    BookRepository bookRepository;

    @Autowired
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
        order.setUserId(user.getId());
        order.setStatus("Заказ принят");
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

        System.out.println(orderRepository.findAllByUser_id(user.getId()).contains(id));

        if (orderRepository.findAllByUser_id(user.getId()).contains(id))
            throw new Exception("GET OUT THE WAY");

        OrderEntity order = orderRepository.findById(id).get();

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

        return orderRepository.save(new OrderEntity(
                order.getId(),
                order.getUserId(),
                order.getStatus(),
                order.getCreatedAt(),
                order.getBooks()
        )).toDTO();
    }

    private List<BookEntity> findBooks(List<Long> booksIds) {
        return bookRepository.findAllByIdIn(booksIds);
    }


    @Override
    public OrderDTO updateAdmin(Long id, SaveOrderDTO orderDTO) {
        OrderEntity order = orderRepository.findById(id).get();

        order.setStatus(orderDTO.getStatus());

        return orderRepository.save(new OrderEntity(
                order.getId(),
                order.getUserId(),
                order.getStatus(),
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
        return orderRepository.findAllByUser_id(user.getId());
    }
}