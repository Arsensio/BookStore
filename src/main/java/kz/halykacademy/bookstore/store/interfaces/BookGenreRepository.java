package kz.halykacademy.bookstore.store.interfaces;

import kz.halykacademy.bookstore.model.BookGenreEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface BookGenreRepository extends JpaRepository<BookGenreEntity,Long> {

    @Transactional
    public void deleteAllByGenreId(long authorId);

    @Transactional
    public void deleteAllByBookId(long bookId);

}
