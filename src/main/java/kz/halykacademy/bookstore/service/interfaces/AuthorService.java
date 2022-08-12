package kz.halykacademy.bookstore.service.interfaces;

import kz.halykacademy.bookstore.exceptions.ResourceNotFoundException;
import kz.halykacademy.bookstore.models.AuthorEntity;
import kz.halykacademy.bookstore.models.BookEntity;
import kz.halykacademy.bookstore.store.interfaces.AuthorRepository;
import kz.halykacademy.bookstore.store.interfaces.BookRepository;
import kz.halykacademy.bookstore.web.author.AuthorDTO;
import kz.halykacademy.bookstore.web.author.SaveAuthorDTO;
import kz.halykacademy.bookstore.web.book.BookDTO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Collectors;

@Service
public interface AuthorService {

    List<AuthorDTO> findAll();

    AuthorDTO findOne(Long id) throws Throwable;

    List<AuthorDTO> findAllByFirstName(String name);

    AuthorDTO save(SaveAuthorDTO saveAuthor);

    void delete(Long id);

    AuthorDTO update(Long id,SaveAuthorDTO saveAuthor);

    LinkedHashSet<AuthorDTO> findAllByGenre(List<Long> ids);

    List<BookDTO> findAllByFullName(String name) ;
}

@Service
@AllArgsConstructor
class AuthorServiceImpl implements AuthorService {

    AuthorRepository repository;

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
                .orElseThrow((Supplier<Throwable>) () -> new ResourceNotFoundException("Author with id not found"));

    }

    @Override
    public List<AuthorDTO> findAllByFirstName(String name) {
        return repository.findAllByFirstNameContainingIgnoreCase(name).stream().map(AuthorEntity::toDto).toList();
    }

    @Override
    public AuthorDTO save(SaveAuthorDTO saveAuthor) {
        AuthorEntity saved = repository.save(
                new AuthorEntity(
                        null,
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

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }

    @Override
    public AuthorDTO update(Long id,SaveAuthorDTO saveAuthor) {
        repository.findById(id).ifPresent(authorEntity -> {
            authorEntity.setFirstName(saveAuthor.getFirstName());
            authorEntity.setLastName(saveAuthor.getLastName());
            authorEntity.setPatronymic(saveAuthor.getPatronymic());
            authorEntity.setDateOfBirth(saveAuthor.getDateOfBirth());
            authorEntity.setCreatedAt(authorEntity.getCreatedAt());
            repository.saveAndFlush(authorEntity);
        });
        return repository.findById(id).get().toDto();
    }

    @Override
    public LinkedHashSet<AuthorDTO> findAllByGenre(List<Long> ids) {
        LinkedHashSet<AuthorDTO> authorFound = new LinkedHashSet<>();
        authorFound.addAll(repository.findAllByBooks_Genres_IdIn(ids).stream().map(AuthorEntity::toDto).collect(Collectors.toList()));
        return authorFound;
    }

    @Override
    public List<BookDTO> findAllByFullName(String name) {
        return null;
    }


}

