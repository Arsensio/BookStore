package kz.halykacademy.bookstore.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


//@Configuration
public class SecurityConfiguration {
//    @Bean
//    public InMemoryUserDetailsManager userDetailsService() {
//        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
//        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
//        manager.createUser(User
//                .withUsername("user1")
//                .password(encoder().encode("user1"))
//                .roles("USER")
//                .build());
//
//        manager.createUser(User
//                .withUsername("admin1")
//                .password(encoder().encode("admin1"))
//                .roles("ADMIN")
//                .build());
//        return manager;
//    }
//
//    @Bean
//    public static PasswordEncoder encoder() {
//        return new BCryptPasswordEncoder();
//    }
//
//
//    @Bean
//    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//        http
//                .csrf().disable()
//                .authorizeHttpRequests()
//                .anyRequest().authenticated().and().httpBasic();
//        return http.build();
//    }
}