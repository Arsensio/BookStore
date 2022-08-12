package kz.halykacademy.bookstore.controllers;

import kz.halykacademy.bookstore.service.interfaces.BookService;
import kz.halykacademy.bookstore.web.book.BookDTO;
import kz.halykacademy.bookstore.web.book.SaveBookDTO;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedHashSet;
import java.util.List;


@CrossOrigin(origins = "http://localhost:3000/")
@RestController
@AllArgsConstructor
@RequestMapping("/books")
public class BookController {

    private BookService service;

    @GetMapping
    public List<BookDTO> listAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public BookDTO getOne(@PathVariable Long id) throws Throwable {
        return service.findOne(id);
    }

    @GetMapping("/name")
    public List<BookDTO> getByName(@RequestParam("name") String name) throws Throwable {
        return service.findOneByName(name);
    }

    @GetMapping("/authors")
    public List<BookDTO> getByAuthors(@RequestParam("name") String name) {
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

    @PutMapping("/update/{id}")
    public BookDTO update(@PathVariable Long id,@RequestBody SaveBookDTO saveBookDTO) {
        return service.update(id,saveBookDTO);
    }

    @GetMapping("/genres")
    public LinkedHashSet<BookDTO> getByGenre(@RequestBody List<Long> genresId) {
        return service.findAllByGenre(genresId);
    }
}
