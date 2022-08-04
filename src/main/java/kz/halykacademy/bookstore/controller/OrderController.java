package kz.halykacademy.bookstore.controller;


import kz.halykacademy.bookstore.service.interfaces.OrderService;
import kz.halykacademy.bookstore.web.order.OrderDTO;
import kz.halykacademy.bookstore.web.order.SaveOrderDTO;
import kz.halykacademy.bookstore.web.user.SaveUserDTO;
import kz.halykacademy.bookstore.web.user.UserDTO;
import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/orders")
public class OrderController {
    @Autowired
    OrderService orderService;

    @GetMapping
    public List<OrderDTO> findAll() {
        return orderService.findAll();
    }

    @GetMapping("/{id}")
    public OrderDTO findOne(@RequestParam Long id) throws Throwable {
        return orderService.findOne(id);
    }


    @PostMapping
    public OrderDTO save(@RequestBody SaveOrderDTO saveOrderDTO) throws Exception {
        return orderService.save(saveOrderDTO);
    }

    @PutMapping
    public OrderDTO update(@RequestBody SaveOrderDTO orderDTO) {
        return orderService.update(orderDTO);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        orderService.delete(id);
    }
}
