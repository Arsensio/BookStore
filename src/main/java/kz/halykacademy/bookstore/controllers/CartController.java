package kz.halykacademy.bookstore.controllers;


import kz.halykacademy.bookstore.service.interfaces.CartService;
import kz.halykacademy.bookstore.web.cart.CartDTO;
import kz.halykacademy.bookstore.web.cart.SaveCartDTO;
import lombok.AllArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000/")
@RestController
@AllArgsConstructor
@RequestMapping("/cart")
public class CartController {

    CartService cartService;

    @PostMapping
    public CartDTO save(@AuthenticationPrincipal UserDetails userDetails, @RequestBody SaveCartDTO saveCartDTO) {
        return cartService.save(userDetails, saveCartDTO);
    }

    @GetMapping("/admin")
    public List<CartDTO> findAll() {
      return cartService.findAll();
    }

    @GetMapping
    public List<CartDTO> findAllUsers(@AuthenticationPrincipal UserDetails userDetails){
        return cartService.findAllByUserId(userDetails);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){
        cartService.delete(id);
    }

    @DeleteMapping
    public void deleteAll(@AuthenticationPrincipal UserDetails userDetails){
        cartService.deleteAll(userDetails);
    }

}
