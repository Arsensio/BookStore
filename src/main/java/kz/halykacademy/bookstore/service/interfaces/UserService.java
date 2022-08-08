package kz.halykacademy.bookstore.service.interfaces;

import kz.halykacademy.bookstore.models.UserEntity;
import kz.halykacademy.bookstore.store.interfaces.UserRepository;
import kz.halykacademy.bookstore.web.user.SaveUserDTO;
import kz.halykacademy.bookstore.web.user.UserDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public interface UserService {

    public List<UserDTO> findAll();

    public UserDTO findOne(Long id) throws Throwable;

    public UserEntity getByName(String name);

    public UserDTO save(SaveUserDTO saveUserDTO);

    public UserDTO updateUsername(UserDetails userDetails, SaveUserDTO saveUserDTO);

    public void delete(Long id);

    public UserDTO updatePassword(UserDetails user, SaveUserDTO saveUserDTO);

    public UserDTO updateRoleandBlocked(UserDetails user, SaveUserDTO saveUserDTO);
}

@Service
@RequiredArgsConstructor
class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
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
        System.out.println(saveUserDTO.getUserName());
        System.out.println(saveUserDTO.getPassword());
        System.out.println(saveUserDTO.getRole());
        System.out.println(saveUserDTO.isBlocked());
        UserEntity saved = userRepository.save(
                new UserEntity(saveUserDTO.getUserName(), encoder.encode(saveUserDTO.getPassword()) ,"USER", false)
        );

        return saved.toDTO();
    }

    @Override
    public UserDTO updateUsername(UserDetails userDetails, SaveUserDTO saveUserDTO) {
        UserEntity user = userRepository.findByUsernameIgnoreCase(userDetails.getUsername()).get();

        userRepository.save(
                new UserEntity(
                        user.getId(),
                        saveUserDTO.getUserName(),
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
    public UserDTO updateRoleandBlocked(UserDetails userDetails, SaveUserDTO saveUserDTO) {
        UserEntity user = userRepository.findById(saveUserDTO.getId()).get();

        userRepository.save(
                new UserEntity(
                        user.getId(),
                        user.getUsername(),
                        user.getPassword(),
                        saveUserDTO.getRole(),
                        saveUserDTO.isBlocked()
                )
        );
        return userRepository.findById(user.getId()).get().toDTO();
    }
}
