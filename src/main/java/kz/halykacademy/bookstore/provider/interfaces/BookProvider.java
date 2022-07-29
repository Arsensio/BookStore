package kz.halykacademy.bookstore.provider.interfaces;

import kz.halykacademy.bookstore.model.BookEntity;

import java.util.List;

public interface BookProvider {

    public List<BookEntity> getAll();

    public BookEntity get(Long id);

    public List<BookEntity> get(String bookName);

    public Long save(BookEntity book);

    public void update(BookEntity book);

    public void delete(BookEntity book);

}
