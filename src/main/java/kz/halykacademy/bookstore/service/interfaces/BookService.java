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
import org.springframework.cglib.core.Converter;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public interface BookService {

    Page<BookDTO> findAll(Pageable pageable);

    BookDTO findOne(Long id) throws Throwable;

    List<BookDTO> findOneByName(String name);

    BookDTO save(SaveBookDTO book);

    void delete(Long id);

    BookDTO update(Long id, SaveBookDTO saveBookDTO);

    LinkedHashSet<BookDTO> findAllByGenre(List<Long> ids);

    @Transactional(readOnly = true)
    List<BookDTO> findAllByAuthors(Long name);
}

@Service
@AllArgsConstructor
class BookServiceImpl implements BookService {

    private BookRepository repository;

    private AuthorRepository authorRepository;

    private PublisherRepository publisherRepository;

    private GenreRepository genreRepository;

    @Override
    public Page<BookDTO> findAll(Pageable pageable) {
        Page<BookEntity> found = repository.findAll(pageable);

        Page<BookDTO> converted = found.map(new Function<BookEntity, BookDTO>() {
            @Override
            public BookDTO apply(BookEntity entity) {
                return entity.toDto();
            }
        });

        return converted;
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
    public BookDTO update(Long id, SaveBookDTO saveBookDTO) {

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
        if (ids.size()==0){
            List<BookDTO> bookFound = repository.findAll(PageRequest.of(0, 20)).stream().map(BookEntity::toDto).collect(Collectors.toList());
            return bookFound.stream().collect(Collectors.toCollection(LinkedHashSet::new));
        }
        LinkedHashSet<BookEntity> booksFound = new LinkedHashSet<>();
        booksFound.addAll(repository.findAllByGenres_IdIn(ids).stream().collect(Collectors.toList()));

        //sort by intersection
        LinkedHashMap<BookEntity, Integer> map = new LinkedHashMap<>();

        booksFound.forEach(bookDTO -> {
            map.put(bookDTO, getIntersections(bookDTO, ids));
            System.out.println(bookDTO.getName() + " " + getIntersections(bookDTO, ids));
        });

        LinkedHashSet<BookDTO> sorted = new LinkedHashSet<>();

        map.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .forEachOrdered(x -> sorted.add(x.getKey().toDto()));

        return sorted;
    }

    public Integer getIntersections(BookEntity book, List<Long> ids) {
        AtomicInteger count = new AtomicInteger(0);

        ids.forEach(id -> {
            if (book.getGenresIds().contains(id))
                count.incrementAndGet();
        });

        return count.get();
    }

    @Override
    public List<BookDTO> findAllByAuthors(Long id) {
        List<BookDTO> booksFound = repository.findAllByAuthors_id(id).stream().map(BookEntity::toDto).collect(Collectors.toList());
        return booksFound;
    }


}


