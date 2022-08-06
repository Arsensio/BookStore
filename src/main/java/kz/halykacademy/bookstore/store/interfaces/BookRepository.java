package kz.halykacademy.bookstore.store.interfaces;

import kz.halykacademy.bookstore.models.BookEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<BookEntity,Long> {


    List<BookEntity> findAllByNameContainingIgnoreCase(@Param("name") String name);

    @Query(value = "SELECT * FROM books INNER JOIN author_book_table abt on books.id = abt.book_id INNER JOIN authors a on a.id = abt.author_id WHERE first_name LIKE %?1% OR last_name LIKE %?1% OR patronymic LIKE %?1%",nativeQuery = true)
    List<BookEntity> findBookEntitiesByAuthors(@Param("firstName")String firstName);

    @Query(value = "SELECT * FROM books INNER JOIN author_book_table abt on books.id = abt.book_id INNER JOIN authors a on a.id = abt.author_id WHERE first_name LIKE %?1% AND last_name LIKE %?2%",nativeQuery = true)
    List<BookEntity>findBookEntitiesByAuthorsFullName(@Param("firstName")String firstName,@Param("lastName") String lastName);

    List<BookEntity>findAllByGenres_IdIn(List<Long>genresIds);

    List<BookEntity>findAllByIdIn(List<Long>ids);

}
