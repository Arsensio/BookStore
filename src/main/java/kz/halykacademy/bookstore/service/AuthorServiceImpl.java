package kz.halykacademy.bookstore.service;

import kz.halykacademy.bookstore.model.Author;
import kz.halykacademy.bookstore.model.Book;
import kz.halykacademy.bookstore.provider.interfaces.AuthorProvider;
import kz.halykacademy.bookstore.service.interfaces.AuthorService;

import java.util.List;

public class AuthorServiceImpl implements AuthorService {

    AuthorProvider provider;

    public AuthorServiceImpl(AuthorProvider provider) {
        this.provider = provider;
    }

    @Override
    public List<Author> listAll() {
        return provider.getAll();
    }

    @Override
    public Author getOne(Long id) {
        return provider.get(id);
    }

    @Override
    public List<Author> getByName(String name) {
        return provider.get(name);
    }
}
