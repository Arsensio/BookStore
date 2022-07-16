package kz.halykacademy.bookstore.controller.interfaces;

import kz.halykacademy.bookstore.model.Book;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;


//@RestController

@Controller
@RequestMapping("/book")
public interface BookController {

    @GetMapping
    String listAll(Model model);

    @GetMapping("/{id}")
    String getOne(@PathVariable Long id, Model model);

//
//    @GetMapping("/new")
//    String newBookForm(Model model);
//
//    @PostMapping()
//    String save(@RequestParam("image")MultipartFile image, Book book, Model model);
//
//    @GetMapping("/delete/{id}")
//    String delete(@PathVariable Long id,Model model);
//
//    @GetMapping(value = "/image/{id}")
//    HttpEntity<FileSystemResource> image(@PathVariable Long id);


    }
