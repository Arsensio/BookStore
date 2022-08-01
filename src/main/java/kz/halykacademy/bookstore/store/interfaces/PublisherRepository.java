package kz.halykacademy.bookstore.store.interfaces;

import kz.halykacademy.bookstore.model.PublisherEntity;
import kz.halykacademy.bookstore.web.publishers.PublisherDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PublisherRepository extends JpaRepository<PublisherEntity, Long> {

    List<PublisherEntity> findAllByNameContaining(@Param("name")String name);

}
