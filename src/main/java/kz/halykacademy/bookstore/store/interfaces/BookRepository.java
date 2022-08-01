package kz.halykacademy.bookstore.store.interfaces;

import kz.halykacademy.bookstore.model.AuthorEntity;
import kz.halykacademy.bookstore.model.BookEntity;
import kz.halykacademy.bookstore.model.PublisherEntity;
import kz.halykacademy.bookstore.web.books.BookDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


import java.util.List;
import java.util.stream.Stream;

@Repository
public interface BookRepository extends JpaRepository<BookEntity,Long> {


    List<BookEntity> findAllByNameContaining(@Param("name") String name);

    Stream<BookDTO> findAllByAuthor(AuthorEntity author);

    @Modifying
    @Query("update BookEntity b  set b.publisher =: publisher where b.id =: id")
    void updatePublisher(@Param("id") Long id,@Param("publisher") Long publisher);

}
