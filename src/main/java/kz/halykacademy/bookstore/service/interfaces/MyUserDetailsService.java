package kz.halykacademy.bookstore.service.interfaces;

import kz.halykacademy.bookstore.exceptions.ResourceNotFoundException;
import kz.halykacademy.bookstore.model.UserEntity;
import kz.halykacademy.bookstore.store.interfaces.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@RequiredArgsConstructor
public class MyUserDetailsService implements UserDetailsService {
    @Autowired
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity userFound = userRepository.findByUsernameContainingIgnoreCase(username).orElseThrow(()->new ResourceNotFoundException("User Not Found"));

        return org.springframework.security.core.userdetails.User.builder().username(userFound.getUsername())
                .password(userFound.getPassword())
                .authorities(userFound.getUserRole())
                .build();
    }
}
