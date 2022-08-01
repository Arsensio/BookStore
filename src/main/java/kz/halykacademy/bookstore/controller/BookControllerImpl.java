package kz.halykacademy.bookstore.controller;

import kz.halykacademy.bookstore.model.BookEntity;
import kz.halykacademy.bookstore.service.interfaces.BookService;
import kz.halykacademy.bookstore.web.books.BookDTO;
import kz.halykacademy.bookstore.web.books.SaveBookDTO;
import kz.halykacademy.bookstore.web.publishers.PublisherDTO;
import kz.halykacademy.bookstore.web.publishers.SavePublisherDTO;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/books")
public class BookControllerImpl   {

    @Autowired
    private BookService service ;


    @GetMapping
    public List<BookDTO> listAll() {
        List<BookDTO> books = service.findAll();

        return books ;
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


    @PostMapping
    public BookEntity save(@RequestBody SaveBookDTO saveBook) {
        return service.save(saveBook);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }

    @PutMapping("/{id}")
    public BookDTO update(@RequestBody Long id, @RequestBody SaveBookDTO saveBookDTO ){
       return service.update(id,saveBookDTO);
    }

//    @GetMapping("/search")
//    public List<BookDTO> findByParameters(@RequestParam("author_id") Long authorId ){
//        return service.findByAuthor(authorId);
//    }
}
