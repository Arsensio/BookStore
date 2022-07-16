package kz.halykacademy.bookstore.controller.interfaces;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/author")
public interface AuthorController {
    @GetMapping
    String listAll(Model model);

    @GetMapping("/{id}")
    String getOne(@PathVariable Long id, Model model);

    @GetMapping("/name")
    String getByName(@PathVariable String name, Model model);
}
