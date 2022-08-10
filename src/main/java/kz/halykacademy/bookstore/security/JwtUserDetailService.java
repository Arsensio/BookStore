package kz.halykacademy.bookstore.security;

import kz.halykacademy.bookstore.models.UserEntity;
import kz.halykacademy.bookstore.security.jwt.JwtUser;
import kz.halykacademy.bookstore.security.jwt.JwtUserFactory;
import kz.halykacademy.bookstore.service.interfaces.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class JwtUserDetailService implements UserDetailsService {

    final private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity user = userService.getByName(username);

        if (user == null){
            throw new UsernameNotFoundException("USER with username"+username+"NOT FOUND");
        }
        JwtUser jwtUser = JwtUserFactory.create(user);
        return jwtUser;
    }
}
