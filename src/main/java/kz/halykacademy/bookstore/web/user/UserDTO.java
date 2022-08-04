package kz.halykacademy.bookstore.web.user;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UserDTO {
    private Long id;
    private String userName;
    private String password;
    private String role;
    private boolean isBlocked;
}
