package kz.halykacademy.bookstore.store.interfaces;

import kz.halykacademy.bookstore.models.PublisherEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PublisherRepository extends JpaRepository<PublisherEntity, Long> {

    List<PublisherEntity> findAllByNameContainingIgnoreCase(@Param("name")String name);

}
