package kz.halykacademy.bookstore.store.interfaces;

import kz.halykacademy.bookstore.models.AuthorEntity;
import kz.halykacademy.bookstore.models.BookEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.LinkedHashSet;
import java.util.List;

@Repository
public interface AuthorRepository extends JpaRepository<AuthorEntity,Long> {

//    @Query("From AuthorEntity c where c.firstName LIKE  %:name% OR c.lastName LIKE %:name% ")
    List<AuthorEntity> findAllByFirstNameContainingIgnoreCase(String firstName);

    List<AuthorEntity>findAllByBooks_Genres_IdIn(List<Long>ids);

    List<AuthorEntity>findAllByIdIn(LinkedHashSet<Long> ids);

    @Query("FROM AuthorEntity where lower(firstName) like lower(concat('%',?1,'%')) or lower(lastName) like lower(concat('%',?1,'%')) or lower(patronymic) like lower(concat('%',?1,'%'))")
    List<AuthorEntity> findByOneArg(String author);

    @Query("FROM AuthorEntity where (lower(firstName) like lower(concat('%',?1,'%')) and lower(lastName) like lower(concat('%',?2,'%'))) OR (lower(firstName) like lower(concat('%',?2,'%')) and lower(lastName) like lower(concat('%',?1,'%')))")
    List<AuthorEntity> findByTwoArg(String firstName,String lastname);

    @Query("FROM AuthorEntity where (lower(firstName) like lower(concat('%',?1,'%')) and lower(lastName) like lower(concat('%',?2,'%')) and lower(patronymic) like lower(concat('%',?3,'%'))) OR (lower(firstName) like lower(concat('%',?2,'%')) and lower(lastName) like lower(concat('%',?3,'%')) and lower(patronymic) like lower(concat('%',?1,'%'))) OR (lower(firstName) like lower(concat('%',?3,'%')) and lower(lastName) like lower(concat('%',?1,'%')) and lower(patronymic) like lower(concat('%',?2,'%')))")
    List<AuthorEntity> findByThreeArg(String firstName,String lastname,String patronymic);

}
