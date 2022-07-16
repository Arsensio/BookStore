package kz.halykacademy.bookstore.service.interfaces;

import kz.halykacademy.bookstore.model.Book;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface BookService {

    public List<Book> listAll();

    public Book getOne(Long id);

//    public Book save(Book book);
//
//    public void delete(Long id);
}
