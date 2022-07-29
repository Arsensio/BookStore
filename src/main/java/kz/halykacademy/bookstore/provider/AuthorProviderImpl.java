package kz.halykacademy.bookstore.provider;

import kz.halykacademy.bookstore.model.AuthorEntity;
import kz.halykacademy.bookstore.provider.interfaces.AuthorProvider;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Component
@NoArgsConstructor
@AllArgsConstructor
public class AuthorProviderImpl implements AuthorProvider {

    ArrayList<AuthorEntity> authors;

    @Override
    public List<AuthorEntity> getAll() {
        authors = new ArrayList<>();
        return authors.stream()
                .filter(Objects::nonNull)
                .collect(Collectors.collectingAndThen(Collectors.toList(), Collections::unmodifiableList));
    }

    @Override
    public AuthorEntity get(Long id) {
        return authors.get(Math.toIntExact(id));
    }

    @Override
    public List<AuthorEntity> get(String authorName) {
        List<AuthorEntity> authorsWithName = authors
                .stream()
                .filter(name -> name.getFirstName().contains(authorName))
                .collect(Collectors.toList());

        return authorsWithName;
    }

    @Override
    public Long save(AuthorEntity author) {
        authors.add(author);
        Long index = Long.valueOf(authors.size() - 1);
        author.setId(index);
        return index;
    }

    @Override
    public void update(AuthorEntity author) {
        authors.set(Math.toIntExact(author.getId()), author);
    }

    @Override
    public void delete(AuthorEntity author) {
        authors.set(Math.toIntExact(author.getId()), author);
    }
}
