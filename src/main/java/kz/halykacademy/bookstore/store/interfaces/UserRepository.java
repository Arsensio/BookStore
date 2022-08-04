package kz.halykacademy.bookstore.store.interfaces;

import kz.halykacademy.bookstore.model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<UserEntity,Long> {

    public List<UserEntity> findByUsernameContainingIgnoreCase(String username);
}
