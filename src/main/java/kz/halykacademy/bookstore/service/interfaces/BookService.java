package kz.halykacademy.bookstore.service.interfaces;

import kz.halykacademy.bookstore.model.*;
import kz.halykacademy.bookstore.store.interfaces.*;
import kz.halykacademy.bookstore.web.author.AuthorDTO;
import kz.halykacademy.bookstore.web.author.SaveAuthorDTO;
import kz.halykacademy.bookstore.web.books.BookDTO;
import kz.halykacademy.bookstore.web.books.SaveBookDTO;
import kz.halykacademy.bookstore.web.genre.GenreDTO;
import kz.halykacademy.bookstore.web.genre.SaveGenreDTO;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public interface BookService {

    public List<BookDTO> findAll();

    public BookDTO findOne(Long id) throws Throwable;

    public List<BookDTO> findOneByName(String name);

    public BookDTO save(SaveBookDTO book);

    public void delete(Long id);

    public BookDTO update(SaveBookDTO saveBookDTO);

    LinkedHashSet<BookDTO> findAllByGenre(List<Long> ids);

    @Transactional(readOnly = true)
    List<BookDTO> findAllByAuthors(String name);
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
    private GenreRepository genreRepository;

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
                .orElseThrow((Supplier<Throwable>) () -> new Exception("Author with id not found"));

    }

    @Override
    public List<BookDTO> findOneByName(String name) {
        return repository.findAllByNameContainingIgnoreCase(name)
                .stream()
                .map(BookEntity::toDto)
                .toList();
    }

    @Override
    public BookDTO save(SaveBookDTO saveBook) {
        System.out.println(saveBook.getPublisherId());
        BookEntity saved = repository.save(
                new BookEntity(
                        saveBook.getId(),
                        saveBook.getPrice(),
                        publisherRepository.findById(saveBook.getPublisherId()).get(),
                        saveBook.getName(),
                        saveBook.getNumOfPage(),
                        saveBook.getYearOfIssue(),
                        getAuthors(saveBook),
                        getGeners(saveBook)
                )
        );
        return saved.toDto();
    }

    public List<AuthorEntity> getAuthors(SaveBookDTO saveBookDTO) {
        return saveBookDTO.getAuthors().stream().map(id -> authorRepository.findById(id).get()).collect(Collectors.toList());
    }

    public List<GenreEntity> getGeners(SaveBookDTO saveBookDTO) {
        return saveBookDTO.getGenres().stream().map(id -> genreRepository.findById(id).get()).collect(Collectors.toList());
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }


    @Override
    public BookDTO update(SaveBookDTO saveBookDTO) {
//        PublisherEntity publisher = null;
//        if (saveBookDTO.getPublisherId() != null && publisherRepository.findById(saveBookDTO.getPublisher().getId()) != null) {
//            publisher = publisherRepository.findById(saveBookDTO.getPublisher().getId()).get();
//        }
//        PublisherEntity finalPublisher = publisher;
        repository.findById(saveBookDTO.getId()).ifPresent(it -> {
            it.setName(saveBookDTO.getName());
            it.setPublisher(publisherRepository.findById(saveBookDTO.getPublisherId()).get());
            it.setAuthors(getAuthors(saveBookDTO));
            it.setPrice(saveBookDTO.getPrice());
            it.setYearOfIssue(saveBookDTO.getYearOfIssue());
            it.setGenres(getGeners(saveBookDTO));
            repository.saveAndFlush(it);
        });

        return repository.findById(saveBookDTO.getId()).get().toDto();
    }

    @Override
    public LinkedHashSet<BookDTO> findAllByGenre(List<Long>ids) {
        LinkedHashSet<BookDTO> booksFound = new LinkedHashSet<>();

        booksFound.addAll(repository.findAllByGenres_IdIn(ids).stream().map(BookEntity::toDto).collect(Collectors.toList()));

//        booksFound.addAll(repository.findBookEntitiesByGenres(genre).stream().map(BookEntity::toDto).collect(Collectors.toList())) ;
        return booksFound;
    }

    @Override
    public List<BookDTO> findAllByAuthors(String name) {
        System.out.println(name);

        List<BookDTO> booksFound =new ArrayList<>();
        booksFound.addAll(repository.findBookEntitiesByAuthors(name).stream().map(BookEntity::toDto).collect(Collectors.toList()));
        System.out.println(booksFound);
        return booksFound;
    }


}


