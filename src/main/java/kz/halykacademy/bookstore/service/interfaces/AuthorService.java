package kz.halykacademy.bookstore.service.interfaces;

import kz.halykacademy.bookstore.model.*;
import kz.halykacademy.bookstore.store.interfaces.*;
import kz.halykacademy.bookstore.web.author.AuthorDTO;
import kz.halykacademy.bookstore.web.author.SaveAuthorDTO;
import kz.halykacademy.bookstore.web.books.BookDTO;
import kz.halykacademy.bookstore.web.books.SaveBookDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Collectors;

@Service
public interface AuthorService {

    public List<AuthorDTO> findAll();

    public AuthorDTO findOne(Long id) throws Throwable;

    public List<AuthorDTO> findAllByFirstName(String name);

    public AuthorDTO save(SaveAuthorDTO saveAuthor);

    public void delete(Long id);

    public AuthorDTO update(SaveAuthorDTO saveAuthor);
}

@Service
class AuthorServiceImpl implements AuthorService {

    @Autowired
    AuthorRepository repository;

    @Autowired
    BookRepository bookRepository;


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
        return repository.findAllByFirstNameContainingIgnoreCase(name).stream().map(AuthorEntity::toDto).toList();
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
                        null,
                        LocalDateTime.now()
                )
        );
        return saved.toDto();
    }

//
//    public List<BookEntity> getBooks(SaveAuthorDTO saveAuthor){
//        List<BookEntity>listOfBook = new ArrayList<>();
//        saveAuthor.getBooks().forEach(it -> {
//            BookEntity book;
//            if (it.getId() == null || !bookRepository.existsById(it.getId())) {
//                book = bookService.save(new SaveBookDTO(it.getId(), it.getPrice(),it.getPublisher(),it.getName(), it.getNumOfpage(), it.getYearOfIssue(),it.getAuthors(),it.getGenres()));
//            } else {
//                book = bookRepository.findById(it.getId()).get();
//            }
//            listOfBook.add(book);
//        });
//        return listOfBook;
//    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }

    @Override
    public AuthorDTO update(SaveAuthorDTO saveAuthor) {
        repository.findById(saveAuthor.getId()).ifPresent(authorEntity -> {
            authorEntity.setFirstName(saveAuthor.getFirstName());
            authorEntity.setLastName(saveAuthor.getLastName());
            authorEntity.setPatronymic(saveAuthor.getPatronymic());
            authorEntity.setDateOfBirth(saveAuthor.getDateOfBirth());
            authorEntity.setCreatedAt(authorEntity.getCreatedAt());
            authorEntity.setBooks(getBooks(saveAuthor.getBooksIds()));
            repository.saveAndFlush(authorEntity);
        });
        return repository.findById(saveAuthor.getId()).get().toDto();
    }

    private List<BookEntity> getBooks(List<Long>ids) {
        System.out.println(ids);
//        System.out.println(bookRepository.findById(saveAuthor.getBooksIds().get(0)).get());
        return ids.stream().map(id -> bookRepository.findById(id).get()).collect(Collectors.toList());
    }





}

