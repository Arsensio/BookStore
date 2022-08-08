package kz.halykacademy.bookstore.store.interfaces;

import kz.halykacademy.bookstore.models.OrderEntity;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<OrderEntity, Long> {
    @Override
    List<OrderEntity> findAll();

    @Query("FROM OrderEntity WHERE user_id =:user_id")
    List<OrderEntity> findAllByUser_id(Long user_id);


}
