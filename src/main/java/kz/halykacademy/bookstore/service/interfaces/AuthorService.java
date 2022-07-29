package kz.halykacademy.bookstore.service.interfaces;

import kz.halykacademy.bookstore.model.AuthorEntity;
import kz.halykacademy.bookstore.model.BookEntity;
import kz.halykacademy.bookstore.model.PublisherEntity;
import kz.halykacademy.bookstore.store.interfaces.AuthorRepository;
import kz.halykacademy.bookstore.web.author.AuthorDTO;
import kz.halykacademy.bookstore.web.author.SaveAuthorDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.function.Supplier;

@Service
public interface AuthorService {

    public List<AuthorDTO> findAll();

    public AuthorDTO findOne(Long id) throws Throwable;

    public List<AuthorDTO> findByName(String name);

    public AuthorDTO save(SaveAuthorDTO saveAuthor);

    public void delete(Long id);
}

@Service
class AuthorServiceImpl implements AuthorService {

    @Autowired
    AuthorRepository repository;


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
    public List<AuthorDTO> findByName(String name) {
        return repository.findByFirstName(name).stream().map(AuthorEntity::toDto).toList();
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
        return saved.toDto();
    }

    @Override
    public void delete(Long id){
        repository.deleteById(id);
    }


}

