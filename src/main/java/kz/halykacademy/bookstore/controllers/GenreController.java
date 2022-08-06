package kz.halykacademy.bookstore.controllers;


import kz.halykacademy.bookstore.models.GenreEntity;
import kz.halykacademy.bookstore.service.interfaces.GenreService;
import kz.halykacademy.bookstore.web.genre.GenreDTO;
import kz.halykacademy.bookstore.web.genre.SaveGenreDTO;
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

    @PutMapping
    public GenreDTO update(@RequestBody SaveGenreDTO saveGenreDTO){
        return service.update(saveGenreDTO);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){
        service.delete(id);
    }
}
