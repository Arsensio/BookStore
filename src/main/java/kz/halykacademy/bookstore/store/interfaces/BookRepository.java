package kz.halykacademy.bookstore.store.interfaces;

import kz.halykacademy.bookstore.model.AuthorEntity;
import kz.halykacademy.bookstore.model.BookEntity;
import kz.halykacademy.bookstore.web.books.BookDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import java.util.stream.Stream;

@Repository
public interface BookRepository extends JpaRepository<BookEntity,Long> {

    Stream<BookDTO> findAllByAuthorId(Long authorId);

    Stream<BookDTO> findAllByAuthor(AuthorEntity author);
}
