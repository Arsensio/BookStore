package kz.halykacademy.bookstore.controller;

import kz.halykacademy.bookstore.model.BookEntity;
import kz.halykacademy.bookstore.service.interfaces.BookService;
import kz.halykacademy.bookstore.web.books.BookDTO;
import kz.halykacademy.bookstore.web.books.SaveBookDTO;
import kz.halykacademy.bookstore.web.publishers.PublisherDTO;
import kz.halykacademy.bookstore.web.publishers.SavePublisherDTO;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.repository.query.Param;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import java.util.LinkedHashSet;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/books")
public class BookControllerImpl {

    @Autowired
    private BookService service;


    @GetMapping
    public List<BookDTO> listAll() {
        List<BookDTO> books = service.findAll();

        return books;
    }

    @GetMapping("/{id}")
    public BookDTO getOne(@PathVariable Long id) throws Throwable {
        BookDTO book = service.findOne(id);

        return book;
    }

    @GetMapping("/name")
    public List<BookDTO> getByName(@RequestParam("name") String name) throws Throwable {
        System.out.println(name);
        return service.findOneByName(name);
    }

    @GetMapping("/authors")
    public List<BookDTO> getByAuthors(@RequestParam("name") String name) {
        System.out.println(name);
        return service.findAllByAuthors(name);
    }

    @PostMapping
    public BookDTO save(@RequestBody SaveBookDTO saveBook) {
        return service.save(saveBook);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }

    @PutMapping("/update")
    public BookDTO update(@RequestBody SaveBookDTO saveBookDTO) {
        return service.update(saveBookDTO);
    }

    @GetMapping("/genre")
    public LinkedHashSet<BookDTO> getByGenre(@RequestBody List<Long> genresId) {
        return service.findAllByGenre(genresId);
    }
}
