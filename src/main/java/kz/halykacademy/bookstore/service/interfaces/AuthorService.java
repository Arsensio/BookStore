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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
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
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("Author not found. Invalid id supplied!");
        }

        AuthorEntity author = repository.findById(id).get();

        for (BookEntity book : author.getBooks()) {
            book.removeAuthor(author);
        }
        author.getBooks().clear();
        repository.save(author);
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
        LinkedHashSet<AuthorEntity> authorFound = new LinkedHashSet<>(repository.findAllByBooks_Genres_IdIn(ids).stream().toList());

        HashMap<AuthorEntity, Integer> map = new HashMap<>();

        AtomicInteger atomicInteger = new AtomicInteger(0);

        authorFound.forEach(authorEntity -> {
            LinkedHashSet<Long> genres = new LinkedHashSet<>(authorEntity.getBooks().stream().map(BookEntity::getGenresIds).flatMap(Collection::stream).toList());

            ids.forEach(id -> {
                if (genres.contains(id))
                    atomicInteger.incrementAndGet();
            });
            map.put(authorEntity, atomicInteger.get());
        });


        LinkedHashSet<AuthorDTO> sorted = new LinkedHashSet<>();

        map.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .forEachOrdered(x -> sorted.add(x.getKey().toDto()));

        return sorted;
    }

    @Override
    public List<AuthorDTO> findAllByFullName(String name) {
        String[] fio = name.split(" ");
        List<AuthorDTO> authorFound = new ArrayList<>();

        if (fio.length == 1) {
            authorFound.addAll(repository.findByOneArg(fio[0]).stream().map(AuthorEntity::toDto).toList());
        } else if (fio.length == 2) {
            authorFound.addAll(repository.findByTwoArg(fio[0], fio[1]).stream().map(AuthorEntity::toDto).toList());
        } else if (fio.length == 3) {
            authorFound.addAll(repository.findByThreeArg(fio[0], fio[1], fio[2]).stream().map(AuthorEntity::toDto).toList());
        } else {
            return authorFound;
        }
        return authorFound;
    }


}

