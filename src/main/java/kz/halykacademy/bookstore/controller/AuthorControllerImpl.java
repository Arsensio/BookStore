package kz.halykacademy.bookstore.controller;

import kz.halykacademy.bookstore.controller.interfaces.AuthorController;
import kz.halykacademy.bookstore.model.Author;
import kz.halykacademy.bookstore.model.Book;
import kz.halykacademy.bookstore.service.interfaces.AuthorService;
import org.springframework.ui.Model;

import java.util.List;

public class AuthorControllerImpl implements AuthorController {

    AuthorService authorService;

    public AuthorControllerImpl(AuthorService authorService) {
        this.authorService = authorService;
    }

    @Override
    public String listAll(Model model) {
        return returnList(model);
    }

    private String returnList(Model model) {
        List<Author> authors = authorService.listAll();
        System.out.println(authors);

        model.addAttribute("authors", authors);
        return "author/list";
    }

    @Override
    public String getOne(Long id, Model model) {
        Author author = authorService.getOne(id);

        model.addAttribute("mode", "Update");
        model.addAttribute("modeTitle", "UpdateExisting");
        model.addAttribute("isUpdate", true);
        model.addAttribute("book", author);


        return "author/form";
    }

    @Override
    public String getByName(String name, Model model) {
        List<Author> books = authorService.getByName(name);

        model.addAttribute("mode", "Update");
        model.addAttribute("modeTitle", "UpdateExisting");
        model.addAttribute("isUpdate", true);
        model.addAttribute("book", books);

        return "author/formName";
    }
}
