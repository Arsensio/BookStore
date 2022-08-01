package kz.halykacademy.bookstore.store.interfaces;

import kz.halykacademy.bookstore.model.AuthorBookEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface AuthorBookRepository extends JpaRepository<AuthorBookEntity,Long> {

    @Transactional
    public void deleteAllByAuhtorId(long authorId);

    @Transactional
    public void deleteAllByBookId(long bookId);

}
