package kz.halykacademy.bookstore.service.interfaces;

import kz.halykacademy.bookstore.model.*;
import kz.halykacademy.bookstore.store.interfaces.*;
import kz.halykacademy.bookstore.web.author.AuthorDTO;
import kz.halykacademy.bookstore.web.author.SaveAuthorDTO;
import kz.halykacademy.bookstore.web.book.BookDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.LinkedHashSet;
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

    LinkedHashSet<AuthorDTO> findAllByGenre(List<Long> ids);
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
            repository.saveAndFlush(authorEntity);
        });
        return repository.findById(saveAuthor.getId()).get().toDto();
    }



    private LinkedHashSet<BookEntity> getBooks(List<Long>ids) {
        LinkedHashSet<BookEntity> booksFound = new LinkedHashSet<>();
        System.out.println(ids);
        booksFound.addAll(bookRepository.findAllByIdIn(ids));
        return booksFound;
    }

    @Override
    public LinkedHashSet<AuthorDTO> findAllByGenre(List<Long> ids) {
        LinkedHashSet<AuthorDTO> authorFound = new LinkedHashSet<>();
        authorFound.addAll(repository.findAllByBooks_Genres_IdIn(ids).stream().map(AuthorEntity::toDto).collect(Collectors.toList()));
        return authorFound;
    }





}

