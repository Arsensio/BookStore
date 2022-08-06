package kz.halykacademy.bookstore.providers.interfaces;

import kz.halykacademy.bookstore.models.AuthorEntity;

import java.util.List;

public interface AuthorProvider {

    public List<AuthorEntity> getAll();

    public AuthorEntity get(Long id);

    public List<AuthorEntity> get(String authorName);

    public Long save(AuthorEntity author);

    public void update(AuthorEntity author);

    public void delete(AuthorEntity author);
}
