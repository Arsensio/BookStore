package kz.halykacademy.bookstore.service.interfaces;

import kz.halykacademy.bookstore.exceptions.BlockedUserException;
import kz.halykacademy.bookstore.exceptions.PriceExceedsLimitException;
import kz.halykacademy.bookstore.models.BookEntity;
import kz.halykacademy.bookstore.models.OrderEntity;
import kz.halykacademy.bookstore.models.UserEntity;
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

    public OrderDTO save(Long id, SaveOrderDTO saveUserDTO) throws Exception;

    public OrderDTO update(UserEntity userEntity, Long id, SaveOrderDTO orderDTO);

    public void delete(Long id);

    List<OrderEntity> findAllByUserId(Long username);
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
    public OrderDTO save(Long id, SaveOrderDTO saveOrderDTO) throws Exception {

        if (!isBlocked(id)) {
            List<BookEntity> books = findAllBook(saveOrderDTO.getBooks());
            System.out.println(books.stream().mapToDouble(BookEntity::getPrice).sum());
            if (books.stream().mapToDouble(BookEntity::getPrice).sum() <= 10000.0) {
                OrderEntity saved = orderRepository.saveAndFlush(
                        new OrderEntity(
                                null,
                                id,
                                "Заказ принят",
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
    public OrderDTO update(UserEntity user, Long id, SaveOrderDTO order) {
        OrderEntity foundOrder = orderRepository.findById(id).get();
        if (user.getUserRole().equals("ADMIN")){
          return orderRepository.save(
                   new OrderEntity(
                           foundOrder.getId(),
                           foundOrder.getUser_id(),
                           order.getStatus(),
                           foundOrder.getCreatedAt(),
                           foundOrder.getBooks()
                           )
           ).toDTO();
        }else if (user.getUserRole().equals("USER")){
            return orderRepository.save(
                    new OrderEntity(
                            foundOrder.getId(),
                            foundOrder.getUser_id(),
                            foundOrder.getStatus(),
                            foundOrder.getCreatedAt(),
                            findAllBook(order.getBooks())
                    )
            ).toDTO();
        }
        return null;
    }

    @Override
    public void delete(Long id) {
        orderRepository.deleteById(id);
    }

    @Override
    public List<OrderEntity> findAllByUserId(Long id) {
        return orderRepository.findAllByUser_id(id);
    }
}
