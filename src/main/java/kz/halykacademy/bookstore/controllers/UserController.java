package kz.halykacademy.bookstore.controllers;


import kz.halykacademy.bookstore.service.interfaces.UserService;
import kz.halykacademy.bookstore.web.user.SaveUserDTO;
import kz.halykacademy.bookstore.web.user.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000/")
@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    UserService userService;

    @GetMapping
    public UserDTO findUser(@AuthenticationPrincipal UserDetails userDetails) {
        return userService.getByName(userDetails.getUsername()).toDTO();
    }

    @GetMapping("/admin/users")
    public List<UserDTO>findAll(){
        return userService.findAll();
    }

    @GetMapping("/{id}")
    public UserDTO findOne(@PathVariable Long id) throws Throwable{
        return userService.findOne(id);
    }

    @GetMapping("/name")
    public UserDTO getByName(@RequestParam("name") String name){
        return userService.getByName(name).toDTO();
    }

    @PostMapping
    public UserDTO save(@RequestBody SaveUserDTO saveUserDTO){
        return userService.save(saveUserDTO);
    }

    @PutMapping("/update/username")
    public UserDTO updateUsername(@AuthenticationPrincipal UserDetails user,@RequestBody SaveUserDTO saveUserDTO){
        return userService.updateUsername(user,saveUserDTO);
    }

    @PutMapping("/update/password")
    public UserDTO updatePassword(@AuthenticationPrincipal UserDetails user,@RequestBody SaveUserDTO saveUserDTO){
        return userService.updatePassword(user,saveUserDTO);
    }

    @PutMapping("/update/admin/role")
    public UserDTO updateRole(@AuthenticationPrincipal UserDetails user,@RequestBody SaveUserDTO saveUserDTO){
        return userService.updateRoleandBlocked(user,saveUserDTO);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){
        userService.delete(id);
    }
}
