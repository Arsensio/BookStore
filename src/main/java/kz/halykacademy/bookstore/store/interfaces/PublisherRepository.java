package kz.halykacademy.bookstore.store.interfaces;

import kz.halykacademy.bookstore.model.PublisherEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PublisherRepository extends JpaRepository<PublisherEntity, Long> {

    List<PublisherEntity> findAllByName(String name);
}
