package kz.halykacademy.bookstore.provider;

import kz.halykacademy.bookstore.model.Book;
import kz.halykacademy.bookstore.provider.interfaces.BookProvider;

import java.util.*;
import java.util.stream.Collectors;

public class BookProviderImpl implements BookProvider {

    ArrayList<Book> books = new ArrayList<>();

    @Override
    public Book get(Long id) {
        return books.get(Math.toIntExact(id));
    }

    @Override
    public List<Book> get(String bookName) {
        List<Book> booksWithName = books
                .stream()
                .filter(name -> name.getName().contains(bookName))
                .collect(Collectors.toList());

        return booksWithName;
    }

    @Override
    public List<Book> getAll() {
        return books.stream()
                .filter(Objects::nonNull)
                .collect(Collectors.collectingAndThen(Collectors.toList(), Collections::unmodifiableList));
    }

    @Override
    public Long save(Book book) {
        books.add(book);
        Long index = Long.valueOf(books.size() - 1);
        book.setId(index);
        return index;
    }

    @Override
    public void update(Book book) {
        books.set(Math.toIntExact(book.getId()), book);
    }

    @Override
    public void delete(Book book) {
        books.set(Math.toIntExact(book.getId()), null);
    }
}
