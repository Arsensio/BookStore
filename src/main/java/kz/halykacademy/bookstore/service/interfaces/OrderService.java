package kz.halykacademy.bookstore.service.interfaces;

import kz.halykacademy.bookstore.exceptions.BlockedUserException;
import kz.halykacademy.bookstore.exceptions.PriceExceedsLimitException;
import kz.halykacademy.bookstore.models.BookEntity;
import kz.halykacademy.bookstore.models.OrderEntity;
import kz.halykacademy.bookstore.store.interfaces.BookRepository;
import kz.halykacademy.bookstore.store.interfaces.OrderRepository;
import kz.halykacademy.bookstore.store.interfaces.UserRepository;
import kz.halykacademy.bookstore.web.order.OrderDTO;
import kz.halykacademy.bookstore.web.order.SaveOrderDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public interface OrderService {

    public List<OrderDTO> findAll();

    public OrderDTO findOne(Long id) throws Throwable;

    public OrderDTO save(@RequestBody SaveOrderDTO saveUserDTO) throws Exception;

    public OrderDTO update(@RequestBody SaveOrderDTO user);

    public void delete(@PathVariable Long id);
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
    public OrderDTO save(SaveOrderDTO saveOrderDTO) throws Exception {

        if (!isBlocked(saveOrderDTO.getUser_id())) {

            List<BookEntity> books = findAllBook(saveOrderDTO.getBooks());
            System.out.println(books.stream().mapToDouble(BookEntity::getPrice).sum());

            if (books.stream().mapToDouble(BookEntity::getPrice).sum() <= 10000.0) {
                OrderEntity saved = orderRepository.saveAndFlush(
                        new OrderEntity(
                                saveOrderDTO.getId(),
                                saveOrderDTO.getUser_id(),
                                saveOrderDTO.getStatus(),
                                LocalDateTime.now(),
                                books
                        )
                );
                return saved.toDTO();
            } else {
                throw new PriceExceedsLimitException("The price of books exceeds a certain norm");
            }
        } else {
            throw new BlockedUserException("User Is Blocked");
        }
    }

    public List<BookEntity> findAllBook(List<Long> ids) {
        return bookRepository.findAllByIdIn(ids);
    }

    public boolean isBlocked(Long id) {
        return userRepository.findById(id).get().isBlocked();
    }

    @Override
    public OrderDTO update(SaveOrderDTO order) {
        if (!isBlocked(order.getUser_id())) {
            orderRepository.findById(order.getId()).ifPresent(it -> {
                it.setStatus(order.getStatus());
                it.setUser_id(order.getUser_id());
                it.setBooks(findAllBook(order.getBooks()));
                orderRepository.saveAndFlush(it);
            });
            return orderRepository.findById(order.getId()).get().toDTO();
        } else throw new BlockedUserException("User Is blocked");
    }

    @Override
    public void delete(Long id) {
        orderRepository.deleteById(id);
    }
}
