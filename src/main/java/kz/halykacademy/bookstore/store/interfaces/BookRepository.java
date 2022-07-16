package kz.halykacademy.bookstore.store.interfaces;

import kz.halykacademy.bookstore.model.Book;

import java.util.List;

public interface BookRepository {

    List<Book>listAll();

    Book getOne(long id);

    Book save(Book book);

    void delete(long id);
}
