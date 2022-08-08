package kz.halykacademy.bookstore.controllers;


import kz.halykacademy.bookstore.models.OrderEntity;
import kz.halykacademy.bookstore.models.UserEntity;
import kz.halykacademy.bookstore.service.interfaces.OrderService;
import kz.halykacademy.bookstore.service.interfaces.UserService;
import kz.halykacademy.bookstore.web.order.OrderDTO;
import kz.halykacademy.bookstore.web.order.SaveOrderDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/orders")
public class OrderController {
    @Autowired
    OrderService orderService;

    @Autowired
    UserService userService;


    @GetMapping("/{id}")
    public OrderDTO findOne(@RequestParam Long id) throws Throwable {
        return orderService.findOne(id);
    }

    @GetMapping("/admin/orders")
    public List<OrderDTO> findAllOrders() {
        return orderService.findAll();
    }

    @GetMapping
    public List<OrderDTO> findAllByUserId(@AuthenticationPrincipal UserDetails user) {
        Long id = userService.getByName(user.getUsername()).getId();
        return orderService.findAllByUserId(id).stream().map(OrderEntity::toDTO).collect(Collectors.toList());
    }


    @PostMapping
    public OrderDTO save(@AuthenticationPrincipal UserDetails userDetails, @RequestBody SaveOrderDTO saveOrderDTO) throws Exception {
        UserEntity user = userService.getByName(userDetails.getUsername());
        return orderService.save(user.getId(), saveOrderDTO);
    }

    @PutMapping("/update/{id}")
    public OrderDTO update(@AuthenticationPrincipal UserDetails userDetails, @PathVariable Long id, @RequestBody SaveOrderDTO orderDTO) throws Exception {
        System.out.println("IAM HERE");
        UserEntity user = userService.getByName(userDetails.getUsername());
        List<Long> ordersIds = orderService.findAllByUserId(user.getId()).stream().map(OrderEntity::getId).collect(Collectors.toList());

        if (!ordersIds.contains(id)) {
            throw new Exception("GET OUT THE WAY");
        }
        System.out.println("AM HERE");
        return orderService.update(user,id, orderDTO);
    }

    @PutMapping("update/admin/{id}")
    public OrderDTO adminUpdate(@AuthenticationPrincipal UserDetails userDetails, @PathVariable Long id, @RequestBody SaveOrderDTO orderDTO) throws Exception {
        System.out.println("IAM HERE");
        UserEntity user = userService.getByName(userDetails.getUsername());
        System.out.println("AM HERE");
        return orderService.update(user,id, orderDTO);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        orderService.delete(id);
    }
}
