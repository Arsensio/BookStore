package kz.halykacademy.bookstore;

import kz.halykacademy.bookstore.model.Book;

import java.util.List;

public interface BookProvider {
    public List<Book> getAll();


    public Book get(Long id);
    Long save(Book t);
    void update(Book t);
    void delete(Book t);

}
