package kz.halykacademy.bookstore.controller;

import kz.halykacademy.bookstore.service.interfaces.AuthorService;
import kz.halykacademy.bookstore.web.author.AuthorDTO;
import kz.halykacademy.bookstore.web.author.SaveAuthorDTO;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedHashSet;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/authors")
public class AuthorController {

    @Autowired
    AuthorService authorService;

    @GetMapping
    public List<AuthorDTO> listAll() {
        return authorService.findAll();
    }

    @GetMapping("/{id}")
    public AuthorDTO getOne(@PathVariable Long id) throws Throwable {
        return authorService.findOne(id);
    }

    @GetMapping("/name")
    public List<AuthorDTO> getByName(@RequestParam("name") String name) {
        System.out.println(name);
        return authorService.findAllByFirstName(name);
    }

    @PostMapping
    public AuthorDTO save(@RequestBody SaveAuthorDTO saveAuthor){
        return authorService.save(saveAuthor);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){
        authorService.delete(id);
    }

    @PutMapping
    public void update(@RequestBody SaveAuthorDTO saveAuthorDTO){
        authorService.update(saveAuthorDTO);
    }

    @GetMapping("/genre")
    public LinkedHashSet<AuthorDTO> getByGenre(@RequestBody List<Long> ids) {
        return authorService.findAllByGenre(ids);
    }
}
