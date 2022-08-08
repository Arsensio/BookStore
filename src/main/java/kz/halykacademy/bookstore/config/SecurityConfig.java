package kz.halykacademy.bookstore.config;

import kz.halykacademy.bookstore.security.jwt.JwtConfigurer;
import kz.halykacademy.bookstore.security.jwt.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final JwtTokenProvider jwtTokenProvider;

    private static final String ADMIN_ENDPOINT = "";
    private static final String LOGIN_ENDPOINT = "/auth/login";


    @Autowired
    public SecurityConfig(JwtTokenProvider jwtTokenProvider) {
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @Bean
    @Override
    protected AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.
                httpBasic().disable()
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .antMatchers(LOGIN_ENDPOINT).permitAll()
                .antMatchers(HttpMethod.POST,"/users").permitAll()
                .antMatchers(HttpMethod.GET,"/books/**","/authors/**","/publishers/**").permitAll()
                .antMatchers(HttpMethod.GET,"/users/admin/**","/orders/admin/orders").hasAuthority("ADMIN")
                .antMatchers(HttpMethod.PUT,"/orders/update/admin/{id}","users/update/admin/{id}","/users/update/admin/role").hasAuthority("ADMIN")
                .antMatchers(HttpMethod.GET,"/orders/{id}","/users").hasAnyAuthority("USER","ADMIN")
                .antMatchers(HttpMethod.PUT,"/orders/update/{id}","users/update/username").hasAnyAuthority("USER","ADMIN")
                .antMatchers(HttpMethod.POST,"/orders").hasAnyAuthority("USER","ADMIN")
                .anyRequest().authenticated()
                .and()
                .apply(new JwtConfigurer(jwtTokenProvider));
    }
}
