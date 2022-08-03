package kz.halykacademy.bookstore.controller;


import kz.halykacademy.bookstore.model.GenreEntity;
import kz.halykacademy.bookstore.service.interfaces.GenreService;
import kz.halykacademy.bookstore.service.interfaces.PublisherService;
import kz.halykacademy.bookstore.web.genre.GenreDTO;
import kz.halykacademy.bookstore.web.genre.SaveGenreDTO;
import kz.halykacademy.bookstore.web.publishers.PublisherDTO;
import kz.halykacademy.bookstore.web.publishers.SavePublisherDTO;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/genres")
public class GenreController {


    @Autowired
    private GenreService service;


    @GetMapping
    public List<GenreDTO> listAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public GenreEntity getOne(@PathVariable Long id) throws Throwable {
        return service.findOne(id);
    }

    @GetMapping("/name")
    public List<GenreDTO> getByName(@RequestParam("name")String name) {
        return service.findAllByName(name);
    }


    @PostMapping
    public GenreEntity save(@RequestBody SaveGenreDTO saveGenreDTO) {
        return service.save(saveGenreDTO);
    }

    @PutMapping("/{id}")
    public GenreDTO update(@PathVariable Long id,@RequestBody SaveGenreDTO saveGenreDTO){
        return service.update(id,saveGenreDTO);
    }
}
