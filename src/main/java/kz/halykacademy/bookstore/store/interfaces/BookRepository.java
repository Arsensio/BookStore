package kz.halykacademy.bookstore.store.interfaces;

import kz.halykacademy.bookstore.models.AuthorEntity;
import kz.halykacademy.bookstore.models.BookEntity;
import kz.halykacademy.bookstore.models.GenreEntity;
import kz.halykacademy.bookstore.web.book.BookDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<BookEntity, Long> {

    Page<BookEntity> findAll(Pageable pageable);

    List<BookEntity> findAllByAuthors_id(Long authorId);

    List<BookEntity> findAllByNameContainingIgnoreCase(@Param("name") String name);

    List<BookEntity> findAllByGenres_IdIn(List<Long> genresIds);

    List<BookEntity> findAllByIdIn(List<Long> ids);

}
