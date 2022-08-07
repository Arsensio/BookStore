package kz.halykacademy.bookstore.config;

import kz.halykacademy.bookstore.exceptions.ResourceNotFoundException;
import kz.halykacademy.bookstore.models.UserEntity;
import kz.halykacademy.bookstore.store.interfaces.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.*;


@RequiredArgsConstructor
public class MyUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity userFound = userRepository.findByUsernameIgnoreCase(username).orElseThrow(() -> new ResourceNotFoundException("User Not Found"));

        if (userFound != null && !userFound.isBlocked()) {
            UserDetails user = User.builder()
                    .username(userFound.getUsername())
                    .password(userFound.getPassword())
                    .authorities(userFound.getUserRole())
                    .accountLocked(userFound.isBlocked())
                    .build();
            return user;
        } else {
            throw new UsernameNotFoundException("User NOT FOUND OR BLOCKED");
        }
    }

}
//class CustomUserDetailCheker implements UserDetailsChecker{
//
//    @Override
//    public void check(UserDetails toCheck) {
//        if (!toCheck.isAccountNonLocked()){
//            throw new BlockedUserException("USER IS BLOCKED");
//        }
//    }
//}
