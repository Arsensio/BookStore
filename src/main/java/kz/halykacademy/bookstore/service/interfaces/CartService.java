package kz.halykacademy.bookstore.service.interfaces;

import kz.halykacademy.bookstore.models.CartEntity;
import kz.halykacademy.bookstore.models.UserEntity;
import kz.halykacademy.bookstore.store.interfaces.BookRepository;
import kz.halykacademy.bookstore.store.interfaces.CartRepository;
import kz.halykacademy.bookstore.store.interfaces.UserRepository;
import kz.halykacademy.bookstore.web.cart.CartDTO;
import kz.halykacademy.bookstore.web.cart.SaveCartDTO;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

public interface CartService {

    CartDTO save(UserDetails userDetails, SaveCartDTO saveCartDTO);

    List<CartDTO> findAll();

    void delete(Long id);

    List<CartDTO> findAllByUserId(UserDetails userDetails);

    void deleteAll(UserDetails userDetails);
}

@Service
@AllArgsConstructor
class CartServiceImpl implements CartService {

    CartRepository cartRepository;

    UserRepository userRepository;

    BookRepository bookRepository;

    @Override
    public CartDTO save(UserDetails userDetails, SaveCartDTO saveCartDTO) {
        UserEntity user = userRepository.findByUsernameIgnoreCase(userDetails.getUsername()).get();

        return cartRepository.save(
                new CartEntity(
                        null,
                        user,
                        bookRepository.findById(saveCartDTO.getBookId()).get(),
                        saveCartDTO.getQuantity()
                )
        ).toDTO();
    }

    @Override
    public List<CartDTO> findAll() {
        return cartRepository.findAll().stream().map(CartEntity::toDTO).collect(Collectors.toList());
    }

    @Override
    public void delete(Long id) {
        cartRepository.deleteById(id);
    }

    @Override
    public List<CartDTO> findAllByUserId(UserDetails userDetails) {
        UserEntity user = userRepository.findByUsernameIgnoreCase(userDetails.getUsername()).get();


        List<CartEntity> cartEntities = cartRepository.findAllByUserId(user).stream().toList();

        for (CartEntity ce : cartEntities) {
            System.out.println(ce.toString());
        }

        return cartEntities.stream().map(CartEntity::toDTO).collect(Collectors.toList());
    }

    @Override
    public void deleteAll(UserDetails userDetails) {
        UserEntity user = userRepository.findByUsernameIgnoreCase(userDetails.getUsername()).get();
        cartRepository.deleteCartEntitiesByUserId(user.getId());
    }
}
