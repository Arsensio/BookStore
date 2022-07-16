package kz.halykacademy.bookstore;

import kz.halykacademy.bookstore.model.Author;

import java.util.List;

public interface AuthorProvider {
    public List<Author>getAll();
}
