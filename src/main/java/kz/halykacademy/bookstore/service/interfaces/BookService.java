package kz.halykacademy.bookstore.service.interfaces;

import kz.halykacademy.bookstore.models.AuthorEntity;
import kz.halykacademy.bookstore.models.BookEntity;
import kz.halykacademy.bookstore.models.GenreEntity;
import kz.halykacademy.bookstore.store.interfaces.AuthorRepository;
import kz.halykacademy.bookstore.store.interfaces.BookRepository;
import kz.halykacademy.bookstore.store.interfaces.GenreRepository;
import kz.halykacademy.bookstore.store.interfaces.PublisherRepository;
import kz.halykacademy.bookstore.web.book.BookDTO;
import kz.halykacademy.bookstore.web.book.SaveBookDTO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public interface BookService {

    List<BookDTO> findAll();

    BookDTO findOne(Long id) throws Throwable;

    List<BookDTO> findOneByName(String name);

    BookDTO save(SaveBookDTO book);

    void delete(Long id);

    BookDTO update(Long id,SaveBookDTO saveBookDTO);

    LinkedHashSet<BookDTO> findAllByGenre(List<Long> ids);

    @Transactional(readOnly = true)
    List<BookDTO> findAllByAuthors(String name);
}

@Service
@AllArgsConstructor
class BookServiceImpl implements BookService {

    private BookRepository repository;

    private AuthorRepository authorRepository;

    private PublisherRepository publisherRepository;

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
                        null,
                        saveBook.getPrice(),
                        saveBook.getBookQuantity(),
                        publisherRepository.findById(saveBook.getPublisherId()).get(),
                        saveBook.getName(),
                        saveBook.getNumOfPages(),
                        saveBook.getBookImage(),
                        saveBook.getYearOfIssue(),
                        getAuthors(saveBook.getAuthors()),
                        getGenres(saveBook.getGenres()),
                        null
                )
        );
        return saved.toDto();
    }

    public List<AuthorEntity> getAuthors(LinkedHashSet<Long> ids) {
        List<AuthorEntity> authorEntities = new ArrayList<>();
        authorEntities.addAll(authorRepository.findAllByIdIn(ids).stream().toList());
        return authorEntities;
    }

    public List<GenreEntity> getGenres(LinkedHashSet<Long> ids) {
        return genreRepository.findAllByIdIn(ids);
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }


    @Override
    public BookDTO update(Long id,SaveBookDTO saveBookDTO) {
        repository.findById(id).ifPresent(it -> {
            it.setName(saveBookDTO.getName());
            it.setPublisher(publisherRepository.findById(saveBookDTO.getPublisherId()).get());
            it.setAuthors(getAuthors(saveBookDTO.getAuthors()));
            it.setNumOfpage(saveBookDTO.getNumOfPages());
            it.setPrice(saveBookDTO.getPrice());
            it.setYearOfIssue(saveBookDTO.getYearOfIssue());
            it.setGenres(getGenres(saveBookDTO.getGenres()));
            repository.saveAndFlush(it);
        });

        return repository.findById(id).get().toDto();
    }

    @Override
    public LinkedHashSet<BookDTO> findAllByGenre(List<Long> ids) {
        LinkedHashSet<BookDTO> booksFound = new LinkedHashSet<>();
        booksFound.addAll(repository.findAllByGenres_IdIn(ids).stream().map(BookEntity::toDto).collect(Collectors.toList()));
        return booksFound;
    }

    @Override
    public List<BookDTO> findAllByAuthors(String name) {
        System.out.println(name);

        String[] fio = name.split(" ");

        List<BookDTO> booksFound = new ArrayList<>();

        if (fio.length == 1) {
            booksFound.addAll(repository.findBookEntitiesByAuthors(fio[0]).stream().map(BookEntity::toDto).collect(Collectors.toList()));
        } else if (fio.length == 2) {
            booksFound.addAll(repository.findBookEntitiesByAuthorsFirstAndLastName(fio[0], fio[1]).stream().map(BookEntity::toDto).collect(Collectors.toList()));
        }

        System.out.println(booksFound);
        return booksFound;
    }


}


