package kz.halykacademy.bookstore.store.interfaces;

import kz.halykacademy.bookstore.models.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity,Long> {

    Optional<UserEntity> findByUsernameContainingIgnoreCase(String username);
}
