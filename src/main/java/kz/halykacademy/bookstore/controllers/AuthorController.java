package kz.halykacademy.bookstore.controllers;

import kz.halykacademy.bookstore.service.interfaces.AuthorService;
import kz.halykacademy.bookstore.web.author.AuthorDTO;
import kz.halykacademy.bookstore.web.author.SaveAuthorDTO;
import kz.halykacademy.bookstore.web.book.BookDTO;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "http://localhost:3000/")
@RestController
@AllArgsConstructor
@RequestMapping("/authors")
public class AuthorController {

    AuthorService authorService;

    @GetMapping
    public ResponseEntity<Page<AuthorDTO>> listAll(Pageable pageable) {
        return new ResponseEntity<>(authorService.findAll(pageable), HttpStatus.OK);
    }


    @GetMapping("/{id}")
    public AuthorDTO getOne(@PathVariable Long id) throws Throwable {
        return authorService.findOne(id);
    }

    @GetMapping("/name")
    public List<AuthorDTO> getByName(@RequestParam("name") String name) {
        return authorService.findAllByFullName(name);
    }

    @PostMapping
    public AuthorDTO save(@RequestBody SaveAuthorDTO saveAuthor) {
        return authorService.save(saveAuthor);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        authorService.delete(id);
    }

    @PutMapping
    public void update(@PathVariable Long id, @RequestBody SaveAuthorDTO saveAuthorDTO) {
        authorService.update(id, saveAuthorDTO);
    }

    @GetMapping("/genre")
    public LinkedHashSet<AuthorDTO> getByGenre(@RequestBody List<Long> ids) {
        return authorService.findAllByGenre(ids);
    }
}
