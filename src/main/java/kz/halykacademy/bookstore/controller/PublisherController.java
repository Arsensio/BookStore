package kz.halykacademy.bookstore.controller;

import kz.halykacademy.bookstore.model.PublisherEntity;
import kz.halykacademy.bookstore.model.PublisherEntity;
import kz.halykacademy.bookstore.service.interfaces.PublisherService;
import kz.halykacademy.bookstore.web.books.BookDTO;
import kz.halykacademy.bookstore.web.publishers.PublisherDTO;
import kz.halykacademy.bookstore.web.publishers.SavePublisherDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/publishers")
public class PublisherController {

    @Autowired
    private PublisherService service;

    public PublisherController(PublisherService service) {
        this.service = service;
    }

    @GetMapping
    public List<PublisherDTO> listAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public PublisherDTO getOne(@PathVariable Long id) throws Throwable {
        return service.findOne(id);
    }

    @GetMapping("/name")
    public List<PublisherEntity> getByName(@PathVariable String name) {
        return service.getByName(name);
    }

    @PostMapping
    public PublisherDTO save(@RequestBody SavePublisherDTO savePublisher) {
        return service.save(savePublisher);
    }
}
