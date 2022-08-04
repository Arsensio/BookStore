package kz.halykacademy.bookstore.model;


import kz.halykacademy.bookstore.web.user.UserDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "user_name", unique = true, nullable = false)
    private String username;

    @Column(name = "password",nullable = false)
    private String password;

    @Column(name = "user_role",nullable = false)
    private String userRole;

    @Column(name = "is_blocked",nullable = false)
    private boolean isBlocked;


    public UserDTO toDTO() {
        return new UserDTO(
                this.id,
                this.username,
                this.password,
                this.userRole,
                this.isBlocked
        );
    }
}
