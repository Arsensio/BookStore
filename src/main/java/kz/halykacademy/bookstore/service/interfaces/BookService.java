package kz.halykacademy.bookstore.service.interfaces;

import kz.halykacademy.bookstore.model.*;
import kz.halykacademy.bookstore.store.interfaces.*;
import kz.halykacademy.bookstore.web.books.BookDTO;
import kz.halykacademy.bookstore.web.books.SaveBookDTO;
import kz.halykacademy.bookstore.web.genre.SaveGenreDTO;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

public interface BookService {

    public List<BookDTO> findAll();

    public BookDTO findOne(Long id) throws Throwable;

    public List<BookDTO>findOneByName(String name);

    public BookEntity save(SaveBookDTO book);

    public void delete(Long id);

    @Transactional(readOnly = true)
    List<BookDTO> findByAuthor(Long authorId);

    public BookDTO update(Long id, SaveBookDTO saveBookDTO);
}

@Service
@AllArgsConstructor
class BookServiceImpl implements BookService {

    @Autowired
    private BookRepository repository;

    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private PublisherRepository publisherRepository;

    @Autowired
    private AuthorBookRepository authorBookRepository;

    @Autowired
    private GenreRepository genreRepository;

    @Autowired
    private BookGenreRepository bookGenreRepository;


    @Autowired
    private GenreService genreService;

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
        return repository.findAllByNameContaining(name)
                .stream()
                .map(BookEntity::toDto)
                .toList();
    }

    @Override
    public BookEntity save(SaveBookDTO book) {
        PublisherEntity publisher = null;
        if (book.getPublisher()!=null){
             publisher = publisherRepository.findById(book.getPublisher().getId()).get();
        }



        List<GenreEntity>genres = List.of();

        BookEntity saved = repository.save(
                new BookEntity(
                        book.getId(),
                        book.getPrice(),
                        publisher,
                        book.getName(),
                        book.getNumOfPage(),
                        book.getYearOfIssue(),
                        book.getAuthors(),
                        genres
                )
        );

        if (book.getGenres() != null) {
            book.getGenres().forEach(it -> {
                        GenreEntity genre;
                        if (it.getId() == null || !genreRepository.existsById(it.getId())) {
                            genre = genreService.save(new SaveGenreDTO(it.getName()));
                        } else {
                            genre = genreRepository.findById(it.getId()).get();
                        }
                        BookGenreEntity join = new BookGenreEntity(saved.getId(),genre.getId());
                        bookGenreRepository.save(join);
            });
        }

        return saved;
    }

    @Override
    public void delete(Long id) {
        authorBookRepository.deleteAllByBookId(id);
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

    @Override
    public BookDTO update(Long id, SaveBookDTO saveBookDTO) {
        PublisherEntity publisher = publisherRepository.findById(saveBookDTO.getPublisher().getId()).get();

        repository.findById(id).ifPresent(it -> {
            it.setName(saveBookDTO.getName());
            it.setPublisher(publisher);
            it.setAuthor(saveBookDTO.getAuthors());
            it.setPrice(saveBookDTO.getPrice());
            it.setYearOfIssue(saveBookDTO.getYearOfIssue());
            repository.saveAndFlush(it);
        });
        return repository.findById(id).get().toDto();
    }


}


