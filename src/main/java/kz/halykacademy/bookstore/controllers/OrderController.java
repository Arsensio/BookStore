package kz.halykacademy.bookstore.controllers;


import kz.halykacademy.bookstore.service.interfaces.OrderService;
import kz.halykacademy.bookstore.web.order.OrderDTO;
import kz.halykacademy.bookstore.web.order.SaveOrderDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/orders")
public class OrderController {
    @Autowired
    OrderService orderService;

//
//    @PreAuthorize("hasAuthority('ADMIN')")
//    @GetMapping
//    public List<OrderDTO> findAll() {
//        return orderService.findAll();
//    }

    @GetMapping("/{id}")
    public OrderDTO findOne(@RequestParam Long id) throws Throwable {
        return orderService.findOne(id);
    }

    @GetMapping
    public List<OrderDTO>findAllByUserId(@AuthenticationPrincipal UserDetails user){
        System.out.println(user.getUsername());
        return orderService.findAllByUserId(user.getUsername());
    }


    @PostMapping
    public OrderDTO save(@RequestBody SaveOrderDTO saveOrderDTO) throws Exception {
        return orderService.save(saveOrderDTO);
    }

    @PutMapping("/update")
    public OrderDTO update(@RequestBody SaveOrderDTO orderDTO) {
        return orderService.update(orderDTO);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        orderService.delete(id);
    }
}
