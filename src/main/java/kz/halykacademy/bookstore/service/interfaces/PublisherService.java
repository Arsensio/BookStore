package kz.halykacademy.bookstore.service.interfaces;

import kz.halykacademy.bookstore.model.Book;
import kz.halykacademy.bookstore.model.Publisher;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PublisherService {

    public List<Publisher> listAll();

    public Publisher getOne(Long id);

    public List<Publisher>getByName(String name);
}
