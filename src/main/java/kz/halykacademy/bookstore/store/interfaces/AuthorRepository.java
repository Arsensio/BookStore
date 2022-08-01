package kz.halykacademy.bookstore.store.interfaces;

import kz.halykacademy.bookstore.model.AuthorEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AuthorRepository extends JpaRepository<AuthorEntity,Long> {

//    @Query("From AuthorEntity c where c.firstName LIKE  %:name% OR c.lastName LIKE %:name% ")
    List<AuthorEntity> findAllByFirstNameContaining(String firstName);
}
