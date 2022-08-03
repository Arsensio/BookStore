package kz.halykacademy.bookstore.store.interfaces;

import kz.halykacademy.bookstore.model.BookEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


import java.util.Collection;
import java.util.List;
import java.util.stream.Stream;

@Repository
public interface BookRepository extends JpaRepository<BookEntity,Long> {


    List<BookEntity> findAllByNameContainingIgnoreCase(@Param("name") String name);

    @Query(value = "SELECT * FROM books INNER JOIN author_book_table abt on books.id = abt.book_id INNER JOIN authors a on a.id = abt.author_id WHERE first_name LIKE %?1%",nativeQuery = true)
    List<BookEntity> findBookEntitiesByAuthors(@Param("firstName")String firstName);

    List<BookEntity>findAllByGenres_IdIn(List<Long>genresIds);

}
