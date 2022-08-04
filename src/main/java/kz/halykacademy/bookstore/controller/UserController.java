package kz.halykacademy.bookstore.controller;


import kz.halykacademy.bookstore.service.interfaces.UserService;
import kz.halykacademy.bookstore.web.user.SaveUserDTO;
import kz.halykacademy.bookstore.web.user.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    UserService userService;

    @GetMapping
    public List<UserDTO> findAll() {
        return userService.findAll();
    }

    @GetMapping("/{id}")
    public UserDTO findOne(Long id) throws Throwable{
        return userService.findOne(id);
    }

    @GetMapping("/name")
    public List<UserDTO> getByName(@RequestParam("name") String name){
        return userService.getByName(name);
    }

    @PostMapping
    public UserDTO save(@RequestBody SaveUserDTO saveUserDTO){
        return userService.save(saveUserDTO);
    }

    @PutMapping
    public UserDTO update(@RequestBody SaveUserDTO user){
        return userService.update(user);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){
        userService.delete(id);
    }
}
