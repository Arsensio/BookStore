package kz.halykacademy.bookstore.service.interfaces;

import kz.halykacademy.bookstore.model.Author;
import kz.halykacademy.bookstore.model.Book;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AuthorService {

    public List<Author> listAll();

    public Author getOne(Long id);

    public List<Author>getByName(String name);
}
