package kz.halykacademy.bookstore.provider.interfaces;

import kz.halykacademy.bookstore.model.Book;

import java.util.List;

public interface BookProvider {

    public List<Book> getAll();

    public Book get(Long id);

    public List<Book> get(String bookName);

    public Long save(Book book);

    public void update(Book book);

    public void delete(Book book);

}
