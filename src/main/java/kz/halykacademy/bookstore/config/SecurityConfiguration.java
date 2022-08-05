package kz.halykacademy.bookstore.config;


import kz.halykacademy.bookstore.service.interfaces.MyUserDetailsService;
import kz.halykacademy.bookstore.store.interfaces.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    @Bean
    public UserDetailsService userDetailsService(UserRepository userRepository){
        return new MyUserDetailsService(userRepository);
    }
//
//    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//        http.authorizeRequests()
//                .antMatchers("/users").hasRole("ADMIN")
//                .antMatchers("books").hasAnyRole("ADMIN", "USER")
//                .and().formLogin();
//        return http.build();
//    }


}