package kz.halykacademy.bookstore.service.interfaces;

import kz.halykacademy.bookstore.model.*;
import kz.halykacademy.bookstore.store.interfaces.*;
import kz.halykacademy.bookstore.web.author.AuthorDTO;
import kz.halykacademy.bookstore.web.author.SaveAuthorDTO;
import kz.halykacademy.bookstore.web.books.SaveBookDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Supplier;

@Service
public interface AuthorService {

    public List<AuthorDTO> findAll();

    public AuthorDTO findOne(Long id) throws Throwable;

    public List<AuthorDTO> findAllByFirstName(String name);

    public AuthorDTO save(SaveAuthorDTO saveAuthor);

    public void delete(Long id);
}

@Service
class AuthorServiceImpl implements AuthorService {

    @Autowired
    AuthorRepository repository;

    @Autowired
    BookRepository bookRepository;

    @Autowired
    AuthorBookRepository authorBookRepasitory;

    @Autowired
    BookGenreRepository bookGenreRepository;

    @Autowired
    GenreRepository genreRepository;


    @Autowired
    BookService bookService;


    @Override
    public List<AuthorDTO> findAll() {
        return repository.findAll()
                .stream()
                .map(AuthorEntity::toDto)
                .toList();
    }

    @Override
    public AuthorDTO findOne(Long id) throws Throwable {
        return repository.findById(id)
                .map(AuthorEntity::toDto)
                .orElseThrow((Supplier<Throwable>) () -> new Exception("Author with id not found"));

    }

    @Override
    public List<AuthorDTO> findAllByFirstName(String name) {
//        List<AuthorEntity> authorEntities = repository.findAllByFirstNameContaining(name);
//        System.out.println(authorEntities.toString());
        return repository.findAllByFirstNameContaining(name).stream().map(AuthorEntity::toDto).toList();
    }

    @Override
    public AuthorDTO save(SaveAuthorDTO saveAuthor) {

        AuthorEntity saved = repository.save(
                new AuthorEntity(
                        saveAuthor.getId(),
                        saveAuthor.getFirstName(),
                        saveAuthor.getLastName(),
                        saveAuthor.getPatronymic(),
                        saveAuthor.getDateOfBirth(),
                        saveAuthor.getBooks(),
                        LocalDateTime.now()
                )
        );

        if (saveAuthor.getBooks() != null) {


            saveAuthor.getBooks().forEach(it -> {
                BookEntity book;
                if (it.getId() == null || !bookRepository.existsById(it.getId())) {
                    book = bookService.save(new SaveBookDTO(it.getId(), it.getPrice(),it.getPublisher(),it.getName(), it.getNumOfpage(), it.getYearOfIssue(),it.getAuthor(),it.getGenres()));
                } else {
                    book = bookRepository.findById(it.getId()).get();
                }
                AuthorBookEntity join = new AuthorBookEntity();
                join.setAuhtorId(saved.getId());
                join.setBookId(book.getId());

                authorBookRepasitory.save(join);
            });
        }


        return saved.toDto();
    }

    @Override
    public void delete(Long id) {
        authorBookRepasitory.deleteAllByAuhtorId(id);
        repository.deleteById(id);
    }


}

