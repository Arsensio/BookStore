package kz.halykacademy.bookstore.controllers;

import kz.halykacademy.bookstore.service.interfaces.PublisherService;
import kz.halykacademy.bookstore.web.publisher.PublisherDTO;
import kz.halykacademy.bookstore.web.publisher.SavePublisherDTO;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000/")
@RestController
@RequestMapping("/publishers")
@AllArgsConstructor
public class PublisherController {

    private PublisherService service;

    @GetMapping
    public List<PublisherDTO> listAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public PublisherDTO getOne(@PathVariable Long id) throws Throwable {
        return service.findOne(id);
    }

    @GetMapping("/name")
    public List<PublisherDTO> getByName(@RequestParam("name") String name) {
        return service.getByName(name);
    }

    @PostMapping
    public PublisherDTO save(@RequestBody SavePublisherDTO savePublisher) {
        return service.save(savePublisher);
    }

    @PutMapping("/{id}")
    public PublisherDTO update(@PathVariable Long id, @RequestBody SavePublisherDTO savePublisherDTO) {
        return service.update(id,savePublisherDTO);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
