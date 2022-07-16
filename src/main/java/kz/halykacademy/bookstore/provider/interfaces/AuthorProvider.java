package kz.halykacademy.bookstore.provider.interfaces;

import kz.halykacademy.bookstore.model.Author;
import kz.halykacademy.bookstore.model.Book;

import java.util.List;

public interface AuthorProvider {

    public List<Author> getAll();

    public Author get(Long id);

    public List<Author> get(String authorName);

    public Long save(Author author);

    public void update(Author author);

    public void delete(Author author);
}
