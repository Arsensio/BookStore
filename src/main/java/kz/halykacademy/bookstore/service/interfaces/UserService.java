package kz.halykacademy.bookstore.service.interfaces;

import kz.halykacademy.bookstore.exceptions.UsernameAlreadyExistException;
import kz.halykacademy.bookstore.models.UserEntity;
import kz.halykacademy.bookstore.store.interfaces.UserRepository;
import kz.halykacademy.bookstore.web.user.SaveUserDTO;
import kz.halykacademy.bookstore.web.user.UserDTO;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public interface UserService {

    List<UserDTO> findAll();

    UserDTO findOne(Long id) throws Throwable;

    UserEntity getByName(String name);

    UserDTO save(SaveUserDTO saveUserDTO);

    UserDTO updateUsername(UserDetails userDetails, SaveUserDTO saveUserDTO);

    void delete(Long id);

    UserDTO updatePassword(UserDetails user, SaveUserDTO saveUserDTO);

    UserDTO updateRoleAndBlocked(Long id, UserDetails user, SaveUserDTO saveUserDTO);
}

@Service
@AllArgsConstructor
class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    PasswordEncoder encoder;

    @PostConstruct
    public void init() {
        Optional<UserEntity> user = userRepository.findByUsernameIgnoreCase("admin");

        if (user.isEmpty()) {
            userRepository.saveAndFlush(
                    new UserEntity("admin", encoder.encode("user"), "ADMIN", false)
            );
        }
    }

    @Override
    public List<UserDTO> findAll() {
        return userRepository.findAll().stream().map(UserEntity::toDTO).collect(Collectors.toList());
    }

    @Override
    public UserDTO findOne(Long id) throws Throwable {
        return userRepository.findById(id).get().toDTO();
    }

    @Override
    public UserEntity getByName(String username) {
        return userRepository.findByUsernameIgnoreCase(username).get();
    }

    @Override
    public UserDTO save(SaveUserDTO saveUserDTO) {
        if (userRepository.findByUsernameIgnoreCase(saveUserDTO.getUsername()).isPresent())
            throw new UsernameAlreadyExistException("Username already exist");

        UserEntity saved = userRepository.save(
                new UserEntity(saveUserDTO.getUsername(), encoder.encode(saveUserDTO.getPassword()), "USER", false)
        );
        return saved.toDTO();

    }

    @Override
    public UserDTO updateUsername(UserDetails userDetails, SaveUserDTO saveUserDTO) {
        UserEntity user = userRepository.findByUsernameIgnoreCase(userDetails.getUsername()).get();

        userRepository.save(
                new UserEntity(
                        user.getId(),
                        saveUserDTO.getUsername(),
                        user.getPassword(),
                        user.getUserRole(),
                        user.isBlocked()
                )
        );

        return userRepository.findById(user.getId()).get().toDTO();
    }

    @Override
    public void delete(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public UserDTO updatePassword(UserDetails userDetails, SaveUserDTO saveUserDTO) {

        UserEntity user = userRepository.findByUsernameIgnoreCase(userDetails.getUsername()).get();

        userRepository.save(
                new UserEntity(
                        user.getId(),
                        user.getUsername(),
                        encoder.encode(saveUserDTO.getPassword()),
                        user.getUserRole(),
                        user.isBlocked()
                )
        );

        return userRepository.findById(user.getId()).get().toDTO();
    }

    @Override
    public UserDTO updateRoleAndBlocked(Long id, UserDetails userDetails, SaveUserDTO saveUserDTO) {
        UserEntity user = userRepository.findById(id).get();

        userRepository.save(
                new UserEntity(
                        user.getId(),
                        user.getUsername(),
                        user.getPassword(),
                        saveUserDTO.getRole(),
                        saveUserDTO.isBlocked()
                )
        );
        return userRepository.findById(id).get().toDTO();
    }
}
