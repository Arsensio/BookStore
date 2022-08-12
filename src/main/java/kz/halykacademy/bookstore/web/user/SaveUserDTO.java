package kz.halykacademy.bookstore.web.user;


import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SaveUserDTO {
    private String username;
    private String password;
    private String role;
    private boolean isBlocked;
}
