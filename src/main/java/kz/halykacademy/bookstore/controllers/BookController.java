package kz.halykacademy.bookstore.controllers;

import kz.halykacademy.bookstore.service.interfaces.BookService;
import kz.halykacademy.bookstore.web.book.BookDTO;
import kz.halykacademy.bookstore.web.book.SaveBookDTO;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<Page<BookDTO>> listAll(Pageable pageable) {
        return new ResponseEntity<>(service.findAll(pageable), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public BookDTO getOne(@PathVariable Long id) throws Throwable {
        return service.findOne(id);
    }

    @GetMapping("/name")
    public List<BookDTO> getByName(@RequestParam("name") String name) throws Throwable {
        return service.findOneByName(name);
    }

    @GetMapping("/authors/{id}")
    public List<BookDTO> getByAuthors(@PathVariable Long id) {
        return service.findAllByAuthors(id);
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
    public BookDTO update(@PathVariable Long id, @RequestBody SaveBookDTO saveBookDTO) {
        return service.update(id, saveBookDTO);
    }

    @GetMapping("/genres")
    public LinkedHashSet<BookDTO> getByGenre(@RequestParam(name = "ids") List<Long> ids) {
        return service.findAllByGenre(ids);
    }
}
