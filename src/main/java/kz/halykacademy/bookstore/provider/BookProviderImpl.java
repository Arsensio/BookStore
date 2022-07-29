package kz.halykacademy.bookstore.provider;

import kz.halykacademy.bookstore.model.BookEntity;
import kz.halykacademy.bookstore.provider.interfaces.BookProvider;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;

@Component
@NoArgsConstructor
@AllArgsConstructor
public class BookProviderImpl implements BookProvider {

    List<BookEntity> books ;

    @Override
    public String toString() {
        return "BookProviderImpl{" +
                "books=" + books +
                '}';
    }

    @Override
    public BookEntity get(Long id) {
        return books.get(Math.toIntExact(id));
    }

    @Override
    public List<BookEntity> get(String bookName) {

        List<BookEntity> booksWithName = books
                .stream()
                .filter(name -> name.getName().contains(bookName))
                .collect(Collectors.toList());

        return booksWithName;
    }

    @Override
    public List<BookEntity> getAll() {
        //nuzhan
        books = new ArrayList<>();
        return books.stream()
                .filter(Objects::nonNull)
                .collect(Collectors.collectingAndThen(Collectors.toList(), Collections::unmodifiableList));
    }

    @Override
    public Long save(BookEntity book) {
        books.add(book);
        Long index = Long.valueOf(books.size() - 1);
        book.setId(index);
        return index;
    }

    @Override
    public void update(BookEntity book) {
        books.set(Math.toIntExact(book.getId()), book);
    }

    @Override
    public void delete(BookEntity book) {
        books.set(Math.toIntExact(book.getId()), null);
    }
}
