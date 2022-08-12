package kz.halykacademy.bookstore.controllers;


import kz.halykacademy.bookstore.models.UserEntity;
import kz.halykacademy.bookstore.security.jwt.JwtTokenProvider;
import kz.halykacademy.bookstore.service.interfaces.UserService;
import kz.halykacademy.bookstore.web.authentication.AuthenticationRequestDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:3000/")
@RestController
@RequestMapping(value = "/auth")
public class AuthenticationController {


    private AuthenticationManager authenticationManager;
    private JwtTokenProvider jwtTokenProvider;
    private UserService userService;

    public AuthenticationController(AuthenticationManager authenticationManager, JwtTokenProvider jwtTokenProvider, UserService userService) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenProvider = jwtTokenProvider;
        this.userService = userService;
    }

    @PostMapping(value = "/login")
    public ResponseEntity login(@RequestBody AuthenticationRequestDTO requestDTO) {
        try {
            String username = requestDTO.getUsername();

            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, requestDTO.getPassword()));

            UserEntity user = userService.getByName(username);
            if (user == null) {
                throw new UsernameNotFoundException("User with" + requestDTO.getUsername() + " not Found");
            }

            System.out.println(username + "; " + user.getUserRole());
            String token = jwtTokenProvider.createToken(username, user.getUserRole());

            Map<Object, Object> response = new HashMap<>();
            response.put("username", username);
            response.put("role", user.getUserRole());
            response.put("token", token);

            return ResponseEntity.ok(response);
        } catch (AuthenticationException e) {
            e.printStackTrace();
            throw new BadCredentialsException("Invalide Username or password");
        }
    }

}
