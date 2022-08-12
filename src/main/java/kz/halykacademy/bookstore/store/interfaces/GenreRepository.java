package kz.halykacademy.bookstore.store.interfaces;

import kz.halykacademy.bookstore.models.GenreEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.LinkedHashSet;
import java.util.List;


@Repository
public interface GenreRepository extends JpaRepository<GenreEntity,Long> {

    List<GenreEntity> findAllByNameContainingIgnoreCase(String name);

    List<GenreEntity>findAllByIdIn(LinkedHashSet<Long>ids);
}
