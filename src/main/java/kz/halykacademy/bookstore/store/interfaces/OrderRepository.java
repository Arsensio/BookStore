package kz.halykacademy.bookstore.store.interfaces;

import kz.halykacademy.bookstore.models.OrderEntity;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<OrderEntity,Long> {

    @Override
    List<OrderEntity> findAll();
}
