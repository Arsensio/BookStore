package kz.halykacademy.bookstore.service.interfaces;

import kz.halykacademy.bookstore.model.BookEntity;
import kz.halykacademy.bookstore.model.PublisherEntity;
import kz.halykacademy.bookstore.store.interfaces.AuthorRepository;
import kz.halykacademy.bookstore.store.interfaces.BookRepository;
import kz.halykacademy.bookstore.web.books.BookDTO;
import kz.halykacademy.bookstore.web.books.SaveBookDTO;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

public interface BookService {

    public List<BookDTO> findAll();

    public BookDTO findOne(Long id) throws Throwable;

    public List<BookDTO>findOneByName(String name);

    public BookDTO save(SaveBookDTO book);

    public void delete(Long id);

    @Transactional(readOnly = true)
    List<BookDTO> findByAuthor(Long authorId);
}

@Service
@AllArgsConstructor
class BookServiceImpl implements BookService {

    @Autowired
    private BookRepository repository;

    private AuthorRepository authorRepository;


    @Override
    public List<BookDTO> findAll() {
        return repository.findAll()
                .stream()
                .map(BookEntity::toDto)
                .toList();
    }

    @Override
    public BookDTO findOne(Long id) throws Throwable {
        return repository.findById(id)
                .map(BookEntity::toDto)
                .orElseThrow((Supplier<Throwable>)()->new Exception("Author with id not found"));

    }

    @Override
    public List<BookDTO> findOneByName(String name) {
        return null;
    }

    @Override
    public BookDTO save(SaveBookDTO book) {
        PublisherEntity publisher = new PublisherEntity();

        BookEntity saved = repository.save(
                new BookEntity(
                        book.getId(),
                        book.getPrice(),
                        new PublisherEntity(book.getPublisher()),
                        book.getName(),
                        book.getNumOfPage(),
                        book.getYearOfIssue(),
                        book.getAuthors()
        )
        );
        return saved.toDto();
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);

    }


    @Override
    @Transactional(readOnly = true)
    public List<BookDTO> findByAuthor(Long authorId) {
        ArrayList<BookDTO> list = new ArrayList<>();

        authorRepository.findById(authorId)
                .ifPresent(
                        author -> list
                                .addAll(
                                        repository.findAllByAuthor(author).toList()
                                )
                );

        return list;

    }



}


