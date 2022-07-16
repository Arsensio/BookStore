package kz.halykacademy.bookstore.provider;

import kz.halykacademy.bookstore.model.Author;
import kz.halykacademy.bookstore.model.Book;
import kz.halykacademy.bookstore.provider.interfaces.AuthorProvider;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class AuthorProviderImpl implements AuthorProvider {

    ArrayList<Author> authors = new ArrayList<>();

    @Override
    public List<Author> getAll() {
        return authors.stream()
                .filter(Objects::nonNull)
                .collect(Collectors.collectingAndThen(Collectors.toList(), Collections::unmodifiableList));
    }

    @Override
    public Author get(Long id) {
        return authors.get(Math.toIntExact(id));
    }

    @Override
    public List<Author> get(String authorName) {
        List<Author> authorsWithName = authors
                .stream()
                .filter(name -> name.getName().contains(authorName))
                .collect(Collectors.toList());

        return authorsWithName;
    }

    @Override
    public Long save(Author author) {
        authors.add(author);
        Long index = Long.valueOf(authors.size() - 1);
        author.setId(index);
        return index;
    }

    @Override
    public void update(Author author) {
        authors.set(Math.toIntExact(author.getId()), author);
    }

    @Override
    public void delete(Author author) {
        authors.set(Math.toIntExact(author.getId()), author);
    }
}
