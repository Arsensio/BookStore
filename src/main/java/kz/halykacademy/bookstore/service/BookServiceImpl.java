package kz.halykacademy.bookstore.service;


import kz.halykacademy.bookstore.BookProvider;
import kz.halykacademy.bookstore.model.Book;
import kz.halykacademy.bookstore.service.interfaces.BookService;
import kz.halykacademy.bookstore.store.interfaces.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
//    private BookRepository repository;

    private BookProvider provider;

    public BookServiceImpl( BookProvider provider) {
//        this.repository = repository;
        this.provider = provider;
    }

    public List<Book> listAll(){
        return provider.getAll();
    }

    public Book getOne(Long id){
        return provider.get(id);
    }

//    public Book save(@NonNull final Book book) {
//        Book existing = repository.getOne(book.getId());
//        if(existing!=null){
//            System.out.println("Book Already exist");
//            return null;
//        }
//        return repository.save(book);
//    }
//
//    public void delete(Long id) {
//        repository.delete(id);
//    }
}

