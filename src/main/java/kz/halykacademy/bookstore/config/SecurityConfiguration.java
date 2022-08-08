//package kz.halykacademy.bookstore.config;
//
//
//import kz.halykacademy.bookstore.exceptions.CustomExceptionHandler;
//import kz.halykacademy.bookstore.store.interfaces.UserRepository;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.http.HttpMethod;
//import org.springframework.security.config.Customizer;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.web.SecurityFilterChain;
//
//
//@Configuration
//@EnableWebSecurity
//public class SecurityConfiguration {
//
//    @Bean
//    public UserDetailsService userDetailsService(UserRepository userRepository) {
//        MyUserDetailsService myUser = new MyUserDetailsService(userRepository);
//        return myUser;
//    }
//
//    @Bean
//    public SecurityFilterChain customFilterChain(HttpSecurity http) throws Exception {
//        return http.csrf().disable().authorizeRequests()
//                .antMatchers(HttpMethod.POST, "/orders").hasAuthority("USER")
//                .antMatchers(HttpMethod.PUT, "/orders/**").hasAuthority("USER")
//                .antMatchers(HttpMethod.GET, "/**").authenticated()
//                .antMatchers("/**").permitAll()
//                .and()
//                .httpBasic(Customizer.withDefaults()).build();
//    }
//}