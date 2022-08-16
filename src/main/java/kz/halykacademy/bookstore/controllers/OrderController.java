package kz.halykacademy.bookstore.controllers;


import kz.halykacademy.bookstore.models.OrderEntity;
import kz.halykacademy.bookstore.service.interfaces.OrderService;
import kz.halykacademy.bookstore.web.order.OrderDTO;
import kz.halykacademy.bookstore.web.order.SaveOrderDTO;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "http://localhost:3000/")
@RestController
@RequestMapping("/orders")
@AllArgsConstructor
public class OrderController {

    OrderService orderService;

    @GetMapping("/{id}")
    public OrderDTO findOne(@PathVariable Long id) throws Throwable {
        return orderService.findOne(id);
    }

    @GetMapping("/admin/orders")
    public Page<OrderDTO> findAllOrders(Pageable pageable) {
        return new PageImpl<>(
                orderService.findAll().stream().skip(pageable.getOffset()).limit(pageable.getPageSize()).collect(Collectors.toList())
        );
    }

    @GetMapping
    public List<OrderDTO> findAllByUserId(@AuthenticationPrincipal UserDetails user) {
        return orderService.findAllByUserId(user).stream().map(OrderEntity::toDTO).collect(Collectors.toList());
    }


    @PostMapping
    public OrderDTO save(@AuthenticationPrincipal UserDetails userDetails, @RequestBody SaveOrderDTO saveOrderDTO) throws Exception {
        return orderService.save(userDetails, saveOrderDTO);
    }

    @PutMapping("/update/{id}")
    public OrderDTO update(@AuthenticationPrincipal UserDetails userDetails, @PathVariable Long id, @RequestBody SaveOrderDTO orderDTO) throws Exception {
        List<Long> ordersIds = orderService.findAllByUserId(userDetails).stream().map(OrderEntity::getId).collect(Collectors.toList());

        if (!ordersIds.contains(id)) {
            throw new Exception("GET OUT THE WAY");
        }

        return orderService.update(userDetails, id, orderDTO);
    }

    @PutMapping("update/admin/{id}")
    public OrderDTO adminUpdate(@PathVariable Long id, @RequestBody OrderDTO status) throws Exception {
        return orderService.updateAdmin(id, status);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        orderService.delete(id);
    }
}
