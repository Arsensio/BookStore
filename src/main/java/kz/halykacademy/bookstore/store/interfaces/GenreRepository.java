package kz.halykacademy.bookstore.store.interfaces;

import kz.halykacademy.bookstore.model.BookEntity;
import kz.halykacademy.bookstore.model.GenreEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface GenreRepository extends JpaRepository<GenreEntity,Long> {

    List<GenreEntity> findAllByNameContainingIgnoreCase(String name);

}
