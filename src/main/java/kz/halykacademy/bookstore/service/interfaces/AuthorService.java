package kz.halykacademy.bookstore.service.interfaces;

import kz.halykacademy.bookstore.exceptions.ResourceNotFoundException;
import kz.halykacademy.bookstore.models.AuthorEntity;
import kz.halykacademy.bookstore.store.interfaces.AuthorRepository;
import kz.halykacademy.bookstore.store.interfaces.BookRepository;
import kz.halykacademy.bookstore.web.author.AuthorDTO;
import kz.halykacademy.bookstore.web.author.SaveAuthorDTO;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;

@Service
public interface AuthorService {

    Page<AuthorDTO> findAll(Pageable pageable);

    AuthorDTO findOne(Long id) throws Throwable;

    List<AuthorDTO> findAllByFirstName(String name);

    AuthorDTO save(SaveAuthorDTO saveAuthor);

    void delete(Long id);

    AuthorDTO update(Long id, SaveAuthorDTO saveAuthor);

    LinkedHashSet<AuthorDTO> findAllByGenre(List<Long> ids);

    List<AuthorDTO> findAllByFullName(String name);
}

@Service
@AllArgsConstructor
class AuthorServiceImpl implements AuthorService {

    AuthorRepository repository;

    BookRepository bookRepository;


    @Override
    public Page<AuthorDTO> findAll(Pageable pageable) {
        Page<AuthorEntity> found = repository.findAll(pageable);
        Page<AuthorDTO> converted = found.map(new Function<AuthorEntity, AuthorDTO>() {
            @Override
            public AuthorDTO apply(AuthorEntity entity) {
                return entity.toDto();
            }
        });
        return converted;
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
    public AuthorDTO update(Long id, SaveAuthorDTO saveAuthor) {
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
    public List<AuthorDTO> findAllByFullName(String name) {
        String[] fio = name.split(" ");
        List<AuthorDTO> authorFound = new ArrayList<>();

        if (fio.length == 1) {
            authorFound.addAll(repository.findByOneArg(fio[0]).stream().map(AuthorEntity::toDto).collect(Collectors.toList()));
        } else if (fio.length == 2) {
            authorFound.addAll(repository.findByTwoArg(fio[0], fio[1]).stream().map(AuthorEntity::toDto).collect(Collectors.toList()));
        } else if (fio.length == 3) {
            authorFound.addAll(repository.findByThreeArg(fio[0], fio[1], fio[2]).stream().map(AuthorEntity::toDto).collect(Collectors.toList()));
        } else {
            return authorFound;
        }
        return authorFound;
    }


}

