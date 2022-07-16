package kz.halykacademy.bookstore.store;

import kz.halykacademy.bookstore.model.Book;
import kz.halykacademy.bookstore.store.interfaces.BookRepository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BookRepositoryImpl implements BookRepository {
    private final Map<Long, Book> store = new HashMap<>();


    @Override
    public List<Book> listAll() {
        return new ArrayList<>(store.values());
    }

    @Override
    public Book getOne(long id) {
        return store.get(id);
    }

    @Override
    public Book save(Book book) {
        long id;
        if (book.getId() == null){
            id = store.size()+1;
            book.setId(id);
        }else {
            id = book.getId();
        }

        store.put(id,book);

        return getOne(id);
    }

    @Override
    public void delete(long id) {
        store.remove(id);
    }
}
